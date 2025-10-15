package com.dohyundev.pos.core.catalog.api

import com.dohyundev.pos.core.catalog.application.query.CatalogQueryServiceV1
import com.dohyundev.pos.core.catalog.dto.Catalog
import com.dohyundev.pos.core.catalog.dto.CatalogQuery
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
@RequestMapping("/api/v1/catalog")
class CatalogApiControllerV1(
    private val catalogQueryService: CatalogQueryServiceV1
) {
    /**
     * 전체 카탈로그 조회 (카테고리 계층 구조 + 상품 목록)
     * GET /api/v1/catalog
     * 
     * @param includeProducts 상품 정보 포함 여부 (default: true)
     * @param includeSubCategories 하위 카테고리 포함 여부 (default: true)
     * @param onlyRootCategories 최상위 카테고리만 조회 여부 (default: true)
     */
    @GetMapping
    fun getCatalog(
        @RequestParam(defaultValue = "true") includeProducts: Boolean,
        @RequestParam(defaultValue = "true") includeSubCategories: Boolean,
        @RequestParam(defaultValue = "true") onlyRootCategories: Boolean
    ): ResponseEntity<Catalog.Catalog> {
        val request = CatalogQuery.GetCatalogRequest(
            includeProducts = includeProducts,
            includeSubCategories = includeSubCategories,
            onlyRootCategories = onlyRootCategories
        )
        val catalog = catalogQueryService.getCatalog(request)
        return ResponseEntity.ok(catalog)
    }

    /**
     * 모든 카테고리 조회 (플랫 리스트)
     * GET /api/v1/catalog/categories
     */
    @GetMapping("/categories")
    fun getAllCategories(): ResponseEntity<List<Catalog.CatalogCategory>> {
        val categories = catalogQueryService.getAllCategories()
        return ResponseEntity.ok(categories)
    }

    /**
     * 특정 카테고리 조회
     * GET /api/v1/catalog/categories/{categoryId}
     */
    @GetMapping("/categories/{categoryId}")
    fun getCategoryById(@PathVariable categoryId: String): ResponseEntity<Catalog.CatalogCategory> {
        val category = catalogQueryService.getCategoryById(categoryId)
        return ResponseEntity.ok(category)
    }

    /**
     * 특정 카테고리의 상품 목록 조회
     * GET /api/v1/catalog/categories/{categoryId}/products
     * 
     * @param includeSubCategories 하위 카테고리 상품 포함 여부 (default: false)
     * @param includeOptions 상품 옵션 정보 포함 여부 (default: false)
     */
    @GetMapping("/categories/{categoryId}/products")
    fun getProductsByCategory(
        @PathVariable categoryId: String,
        @RequestParam(defaultValue = "false") includeSubCategories: Boolean,
        @RequestParam(defaultValue = "false") includeOptions: Boolean
    ): ResponseEntity<List<Catalog.CatalogProduct>> {
        val request = CatalogQuery.GetProductsByCategoryRequest(
            categoryId = categoryId,
            includeSubCategories = includeSubCategories,
            includeOptions = includeOptions
        )
        val products = catalogQueryService.getProductsByCategory(request)
        return ResponseEntity.ok(products)
    }

    /**
     * 전체 상품 목록 조회
     * GET /api/v1/catalog/products
     * 
     * @param includeOptions 상품 옵션 정보 포함 여부 (default: false)
     */
    @GetMapping("/products")
    fun getAllProducts(
        @RequestParam(defaultValue = "false") includeOptions: Boolean
    ): ResponseEntity<List<Catalog.CatalogProduct>> {
        val products = catalogQueryService.getAllProducts(includeOptions)
        return ResponseEntity.ok(products)
    }

    /**
     * 상품 상세 조회
     * GET /api/v1/catalog/products/{productId}
     */
    @GetMapping("/products/{productId}")
    fun getProductDetail(@PathVariable productId: String): ResponseEntity<Catalog.CatalogProduct> {
        val product = catalogQueryService.getProductDetail(productId)
        return ResponseEntity.ok(product)
    }

    /**
     * 상품 검색
     * GET /api/v1/catalog/search
     * 
     * @param keyword 검색 키워드
     * @param categoryId 카테고리 ID
     * @param minPrice 최소 가격
     * @param maxPrice 최대 가격
     * @param hasOptions 옵션 보유 여부
     */
    @GetMapping("/search")
    fun searchProducts(
        @RequestParam(required = false) keyword: String?,
        @RequestParam(required = false) categoryId: String?,
        @RequestParam(required = false) minPrice: BigDecimal?,
        @RequestParam(required = false) maxPrice: BigDecimal?,
        @RequestParam(required = false) hasOptions: Boolean?,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): ResponseEntity<Catalog.CatalogSearchResult> {
        val request = CatalogQuery.SearchProductsRequest(
            keyword = keyword,
            categoryId = categoryId,
            minPrice = minPrice,
            maxPrice = maxPrice,
            hasOptions = hasOptions,
            page = page,
            size = size
        )
        val result = catalogQueryService.searchProducts(request)
        return ResponseEntity.ok(result)
    }

    /**
     * 여러 상품 조회 (ID 목록)
     * POST /api/v1/catalog/products/batch
     * 
     * @param productIds 상품 ID 목록
     * @param includeOptions 옵션 정보 포함 여부 (default: true)
     */
    @PostMapping("/products/batch")
    fun getProductsByIds(
        @RequestBody productIds: List<String>,
        @RequestParam(defaultValue = "true") includeOptions: Boolean
    ): ResponseEntity<List<Catalog.CatalogProduct>> {
        val request = CatalogQuery.GetProductsByIdsRequest(
            productIds = productIds,
            includeOptions = includeOptions
        )
        val products = catalogQueryService.getProductsByIds(request)
        return ResponseEntity.ok(products)
    }
}
