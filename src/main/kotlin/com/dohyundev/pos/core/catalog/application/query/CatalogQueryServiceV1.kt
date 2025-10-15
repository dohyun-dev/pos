package com.dohyundev.pos.core.catalog.application.query

import com.dohyundev.pos.core.catalog.domain.entity.Product
import com.dohyundev.pos.core.catalog.domain.entity.ProductCategory
import com.dohyundev.pos.core.catalog.domain.repository.ProductCategoryRepository
import com.dohyundev.pos.core.catalog.domain.repository.ProductRepository
import com.dohyundev.pos.core.catalog.dto.Catalog
import com.dohyundev.pos.core.catalog.dto.CatalogQuery
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CatalogQueryServiceV1(
    private val productRepository: ProductRepository,
    private val productCategoryRepository: ProductCategoryRepository
) {
    /**
     * 전체 카탈로그 조회 (카테고리 계층 구조 + 상품 목록)
     */
    fun getCatalog(request: CatalogQuery.GetCatalogRequest = CatalogQuery.GetCatalogRequest()): Catalog.Catalog {
        val allCategories = productCategoryRepository.findAll()
        val allProducts = if (request.includeProducts) productRepository.findAll() else emptyList()
        
        // 최상위 카테고리만 필터링
        val rootCategories = if (request.onlyRootCategories) {
            allCategories.filter { it.parent == null }
        } else {
            allCategories
        }
        
        val categoryTrees = rootCategories.map { category ->
            buildCategoryTree(
                category = category,
                allCategories = allCategories,
                allProducts = allProducts,
                includeSubCategories = request.includeSubCategories,
                includeProducts = request.includeProducts
            )
        }
        
        return Catalog.Catalog(
            categories = categoryTrees,
            totalCategories = allCategories.size,
            totalProducts = allProducts.size
        )
    }

    /**
     * 특정 카테고리의 상품 목록 조회
     */
    fun getProductsByCategory(request: CatalogQuery.GetProductsByCategoryRequest): List<Catalog.CatalogProduct> {
        val category = productCategoryRepository.findByIdOrNull(request.categoryId)
            ?: throw IllegalArgumentException("카테고리를 찾을 수 없습니다: ${request.categoryId}")
        
        val products = if (request.includeSubCategories) {
            // 하위 카테고리 포함
            val categoryIds = getAllSubCategoryIds(category, productCategoryRepository.findAll())
            productRepository.findAll().filter { 
                it.category?.id?.toString() in categoryIds || it.category?.id?.toString() == request.categoryId 
            }
        } else {
            // 현재 카테고리만
            productRepository.findAll().filter { it.category?.id?.toString() == request.categoryId }
        }
        
        return if (request.includeOptions) {
            products.map { Catalog.CatalogProduct.from(it) }
        } else {
            products.map { Catalog.CatalogProduct.fromSimple(it) }
        }
    }

    /**
     * 상품 상세 조회
     */
    fun getProductDetail(productId: String): Catalog.CatalogProduct {
        val product = productRepository.findByIdOrNull(productId)
            ?: throw IllegalArgumentException("상품을 찾을 수 없습니다: $productId")
        
        return Catalog.CatalogProduct.from(product)
    }

    /**
     * 전체 상품 목록 조회
     */
    fun getAllProducts(includeOptions: Boolean = false): List<Catalog.CatalogProduct> {
        val products = productRepository.findAll()
        return if (includeOptions) {
            products.map { Catalog.CatalogProduct.from(it) }
        } else {
            products.map { Catalog.CatalogProduct.fromSimple(it) }
        }
    }

    /**
     * 상품 검색
     */
    fun searchProducts(request: CatalogQuery.SearchProductsRequest): Catalog.CatalogSearchResult {
        val allProducts = productRepository.findAll()
        
        val filteredProducts = allProducts.filter { product ->
            var match = true
            
            // 키워드 검색
            if (request.keyword != null) {
                val keyword = request.keyword.lowercase()
                match = match && (
                    product.name.lowercase().contains(keyword) ||
                    product.code.lowercase().contains(keyword) ||
                    product.description?.lowercase()?.contains(keyword) == true
                )
            }
            
            // 카테고리 필터
            if (request.categoryId != null) {
                match = match && product.category?.id?.toString() == request.categoryId
            }
            
            // 가격 범위 필터
            if (request.minPrice != null) {
                match = match && product.basePrice >= request.minPrice
            }
            if (request.maxPrice != null) {
                match = match && product.basePrice <= request.maxPrice
            }
            
            // 옵션 여부 필터
            if (request.hasOptions != null) {
                match = match && (product.productOptionGroups.isNotEmpty() == request.hasOptions)
            }
            
            match
        }
        
        val catalogProducts = filteredProducts.map { Catalog.CatalogProduct.fromSimple(it) }
        
        return Catalog.CatalogSearchResult(
            products = catalogProducts,
            totalCount = catalogProducts.size,
            filter = Catalog.CatalogSearchFilter(
                categoryId = request.categoryId,
                keyword = request.keyword,
                minPrice = request.minPrice,
                maxPrice = request.maxPrice,
                hasOptions = request.hasOptions
            )
        )
    }

    /**
     * 여러 상품 조회 (ID 목록)
     */
    fun getProductsByIds(request: CatalogQuery.GetProductsByIdsRequest): List<Catalog.CatalogProduct> {
        val products = productRepository.findAllById(request.productIds)
        
        return if (request.includeOptions) {
            products.map { Catalog.CatalogProduct.from(it) }
        } else {
            products.map { Catalog.CatalogProduct.fromSimple(it) }
        }
    }

    /**
     * 모든 카테고리 조회
     */
    fun getAllCategories(): List<Catalog.CatalogCategory> {
        val categories = productCategoryRepository.findAll()
        val allProducts = productRepository.findAll()
        
        return categories.map { category ->
            val productCount = allProducts.count { it.category?.id == category.id }
            val level = calculateCategoryLevel(category)
            val path = buildCategoryPath(category)
            
            Catalog.CatalogCategory.from(
                category = category,
                level = level,
                path = path,
                productCount = productCount
            )
        }
    }

    /**
     * 특정 카테고리 조회
     */
    fun getCategoryById(categoryId: String): Catalog.CatalogCategory {
        val category = productCategoryRepository.findByIdOrNull(categoryId)
            ?: throw IllegalArgumentException("카테고리를 찾을 수 없습니다: $categoryId")
        
        val productCount = productRepository.findAll().count { it.category?.id?.toString() == categoryId }
        val level = calculateCategoryLevel(category)
        val path = buildCategoryPath(category)
        
        return Catalog.CatalogCategory.from(
            category = category,
            level = level,
            path = path,
            productCount = productCount
        )
    }

    /**
     * 카테고리 트리 빌드 (재귀)
     */
    private fun buildCategoryTree(
        category: ProductCategory,
        allCategories: List<ProductCategory>,
        allProducts: List<Product>,
        includeSubCategories: Boolean,
        includeProducts: Boolean,
        level: Int = 0
    ): Catalog.CatalogCategoryTree {
        // 현재 카테고리에 속한 상품들
        val categoryProducts = if (includeProducts) {
            allProducts
                .filter { it.category?.id == category.id }
                .map { Catalog.CatalogProduct.fromSimple(it) }
        } else {
            emptyList()
        }
        
        // 하위 카테고리들
        val subCategories = if (includeSubCategories) {
            allCategories
                .filter { it.parent?.id == category.id }
                .map { subCategory ->
                    buildCategoryTree(
                        category = subCategory,
                        allCategories = allCategories,
                        allProducts = allProducts,
                        includeSubCategories = includeSubCategories,
                        includeProducts = includeProducts,
                        level = level + 1
                    )
                }
        } else {
            emptyList()
        }
        
        val path = buildCategoryPath(category)
        val productCount = categoryProducts.size
        
        val catalogCategory = Catalog.CatalogCategory.from(
            category = category,
            level = level,
            path = path,
            productCount = productCount
        )
        
        return Catalog.CatalogCategoryTree(
            category = catalogCategory,
            products = categoryProducts,
            subCategories = subCategories
        )
    }

    /**
     * 카테고리 레벨 계산
     */
    private fun calculateCategoryLevel(category: ProductCategory): Int {
        var level = 0
        var current: ProductCategory? = category
        while (current?.parent != null) {
            level++
            current = current.parent
        }
        return level
    }

    /**
     * 카테고리 경로 생성 (예: "음료 > 커피 > 아메리카노")
     */
    private fun buildCategoryPath(category: ProductCategory): String {
        val path = mutableListOf<String>()
        var current: ProductCategory? = category
        while (current != null) {
            path.add(0, current.name)
            current = current.parent
        }
        return path.joinToString(" > ")
    }

    /**
     * 하위 카테고리 ID 목록 조회 (재귀)
     */
    private fun getAllSubCategoryIds(category: ProductCategory, allCategories: List<ProductCategory>): List<String> {
        val subCategoryIds = mutableListOf<String>()
        
        val children = allCategories.filter { it.parent?.id == category.id }
        children.forEach { child ->
            subCategoryIds.add(child.id.toString())
            subCategoryIds.addAll(getAllSubCategoryIds(child, allCategories))
        }
        
        return subCategoryIds
    }
}
