package com.dohyundev.pos.core.catalog.product

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/catalog/products")
class ProductApiControllerV1(
    private val productQueryService: ProductQueryServiceV1,
    private val productCommandService: ProductCommandServiceV1,
    private val productPositionCommandService: ProductPositionCommandServiceV1,
) {
    @GetMapping
    fun getProducts(): ResponseEntity<List<ProductResponse.Summary>> {
        val products = productQueryService.getProducts()
        return ResponseEntity.ok(products)
    }

    @GetMapping("/{productId}")
    fun getProduct(
        @PathVariable productId: String
    ): ResponseEntity<ProductResponse.Detail> {
        val product = productQueryService.getProduct(productId)
        return ResponseEntity.ok(product)
    }

    @PostMapping
    fun createProduct(
        @Valid @RequestBody request: ProductCommand.CreateProduct
    ): ResponseEntity<Void> {
        val productId = productCommandService.createProduct(request)
        return ResponseEntity.created(URI.create("/api/v1/catalog/products/$productId")).build()
    }

    @PutMapping
    fun updateProduct(
        @Valid @RequestBody request: ProductCommand.UpdateProduct
    ): ResponseEntity<Void> {
        productCommandService.updateProduct(request)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/position")
    fun exchangeProductPosition(
        @Valid @RequestBody request: ProductCommand.ExchangeProductPosition
    ): ResponseEntity<Void> {
        productPositionCommandService.exchangeProductPosition(request)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{productId}")
    fun deleteProduct(
        @PathVariable productId: String
    ): ResponseEntity<Void> {
        productCommandService.deleteProduct(productId)
        return ResponseEntity.noContent().build()
    }
}
