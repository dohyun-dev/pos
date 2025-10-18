package com.dohyundev.pos.core.catalog.product

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/products")
class ProductApiControllerV1(
    private val productQueryService: ProductQueryServiceV1,
    private val productCommandService: ProductCommandServiceV1,
    private val productPositionCommandService: ProductPositionCommandServiceV1,
) {
    @GetMapping
    fun getProducts(): ResponseEntity<List<Any>> {
        //TODO
        return ResponseEntity.ok().build()
    }

    @PostMapping
    fun createProduct(
        @Valid @RequestBody request: ProductCommand.CreateProduct
    ): ResponseEntity<Any> {
        productCommandService.createProduct(request)
        return ResponseEntity.ok().build()
    }

    @PutMapping
    fun updateProduct(
        @Valid @RequestBody request: ProductCommand.UpdateProduct
    ): ResponseEntity<Any> {
        productCommandService.updateProduct(request)
        return ResponseEntity.ok().build()
    }

    @PutMapping
    fun exchangeProductPosition(
        @Valid @RequestBody request: ProductCommand.ExchangeProductPosition
    ): ResponseEntity<Any> {
        productPositionCommandService.exchangeProductPosition(request)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping
    fun deleteProduct(
        @PathVariable productId: String,
    ): ResponseEntity<Any> {
        productCommandService.deleteProduct(productId)
        return ResponseEntity.ok().build()
    }
}