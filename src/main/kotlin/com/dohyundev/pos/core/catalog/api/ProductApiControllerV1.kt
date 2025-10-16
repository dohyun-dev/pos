package com.dohyundev.pos.core.catalog.api

import com.dohyundev.pos.core.catalog.application.command.ProductCommandServiceV1
import com.dohyundev.pos.core.catalog.dto.ProductCommand
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/products")
class ProductApiControllerV1(
    private val productCommandService: ProductCommandServiceV1,
) {
    @PostMapping
    fun createProduct(@Valid @RequestBody request: ProductCommand.CreateProduct): ResponseEntity<Any> {
        val product = productCommandService.createProduct(request)
        return ResponseEntity.ok(mapOf("productId" to product.id))
    }

    @PutMapping("/{productId}")
    fun updateProduct(
        @PathVariable productId: String,
        @Valid @RequestBody request: ProductCommand.UpdateProduct
    ): ResponseEntity<Any> {
        val product = productCommandService.updateProduct(productId, request)
        return ResponseEntity.ok(mapOf("productId" to product.id))
    }

    @DeleteMapping("/{productId}")
    fun deleteProduct(@PathVariable productId: String): ResponseEntity<Void> {
        productCommandService.deleteProduct(productId)
        return ResponseEntity.noContent().build()
    }
}
