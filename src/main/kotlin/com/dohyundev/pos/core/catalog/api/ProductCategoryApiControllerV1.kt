package com.dohyundev.pos.core.catalog.api

import com.dohyundev.pos.core.catalog.application.command.ProductCategoryCommandServiceV1
import com.dohyundev.pos.core.catalog.application.query.ProductCategoryQueryServiceV1
import com.dohyundev.pos.core.catalog.dto.ProductCategoryCommand
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/product-categories")
class ProductCategoryApiControllerV1(
    private val productCategoryCommandService: ProductCategoryCommandServiceV1,
    private val productCategoryQueryService: ProductCategoryQueryServiceV1
) {
    /**
     * 상품 카테고리 생성
     */
    @PostMapping
    fun createProductCategory(
        @Valid @RequestBody request: ProductCategoryCommand.CreateProductCategory
    ): ResponseEntity<Any> {
        val category = productCategoryCommandService.createProductCategory(request)
        return ResponseEntity.ok(mapOf("categoryId" to category.id))
    }

    @GetMapping
    fun getProductCategories(): ResponseEntity<Any> {
        return ResponseEntity.ok(productCategoryQueryService.findAll())
    }

    /**
     * 상품 카테고리 수정
     */
    @PutMapping("/{categoryId}")
    fun updateProductCategory(
        @PathVariable categoryId: String,
        @Valid @RequestBody request: ProductCategoryCommand.UpdateProductCategory
    ): ResponseEntity<Any> {
        val category = productCategoryCommandService.updateProductCategory(categoryId, request)
        return ResponseEntity.ok(mapOf("categoryId" to category.id))
    }

    @PutMapping("/{categoryId}/toggle-active")
    fun toggleActiveProductCategory(
        @PathVariable categoryId: String
    ): ResponseEntity<Any> {
        val category = productCategoryCommandService.toggleActive(categoryId)
        return ResponseEntity.ok().build()
    }

    /**
     * 상품 카테고리 삭제
     */
    @DeleteMapping("/{categoryId}")
    fun deleteProductCategory(@PathVariable categoryId: String): ResponseEntity<Void> {
        productCategoryCommandService.deleteProductCategory(categoryId)
        return ResponseEntity.noContent().build()
    }
}
