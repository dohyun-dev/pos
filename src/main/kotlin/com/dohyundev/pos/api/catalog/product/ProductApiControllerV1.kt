package com.dohyundev.pos.api.catalog.product

import com.dohyundev.pos.core.catalog.product.command.application.ProductCommand
import com.dohyundev.pos.core.catalog.product.command.application.ProductCommandServiceV1
import com.dohyundev.pos.core.catalog.product.dto.ProductDto
import com.dohyundev.pos.core.catalog.product.query.ProductQueryServiceV1
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pos/v1/catalog/products")
class ProductApiControllerV1(
    private val productCommandService: ProductCommandServiceV1,
    private val productQueryService: ProductQueryServiceV1
) {
    @GetMapping
    fun getAllProducts(): ResponseEntity<List<ProductDto>> {
        val products = productQueryService.getAllProducts()
        return ResponseEntity.ok(products)
    }

    @PostMapping("/aggregate")
    fun createProductAggregate(
        @Valid @RequestBody request: ProductCommand.CreateProduct
    ): ResponseEntity<ProductDto> {
        val product = productCommandService.createProduct(request)
        return ResponseEntity.ok(product)
    }


    @PutMapping("/aggregate/{productId}")
    fun updateProductAggregate(
        @PathVariable productId: Long,
        @Valid @RequestBody request: List<ProductCommand.UpdateProduct>
    ): ResponseEntity<List<ProductDto>> {
        val updatedProducts = productCommandService.updateProduct(request)
        return ResponseEntity.ok(updatedProducts)
    }


    @PutMapping("/aggregate")
    fun updateProductAggregate(
        @Valid @RequestBody request: List<ProductCommand.UpdateProduct>
    ): ResponseEntity<List<ProductDto>> {
        val updatedProducts = productCommandService.bulkUpdateProduct(request)
        return ResponseEntity.ok(updatedProducts)
    }

    @DeleteMapping("/aggregate/{productId}")
    fun deleteProductAggregate(
        @PathVariable productId: Long,
    ): ResponseEntity<ProductDto> {
        val deletedProduct = productCommandService.deleteProduct(productId)
        return ResponseEntity.ok(deletedProduct)
    }
}
