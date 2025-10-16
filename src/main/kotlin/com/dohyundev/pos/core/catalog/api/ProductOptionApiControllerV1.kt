package com.dohyundev.pos.core.catalog.api

import com.dohyundev.pos.core.catalog.application.ProductOptionCommandServiceV1
import com.dohyundev.pos.core.catalog.dto.ProductOptionCommand
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/product-options")
class ProductOptionApiControllerV1(
    private val productOptionCommandService: ProductOptionCommandServiceV1
) {
    @PostMapping
    fun createOptionGroup(
        @Valid @RequestBody request: ProductOptionCommand.CreateProductOptionGroup
    ): ResponseEntity<Any> {
        val group = productOptionCommandService.createProductOptionGroup(request)
        return ResponseEntity.ok(mapOf("optionGroupId" to group.id))
    }

    @PutMapping("/{optionGroupId}")
    fun updateOptionGroup(
        @PathVariable optionGroupId: String,
        @Valid @RequestBody request: ProductOptionCommand.CreateProductOptionGroup
    ): ResponseEntity<Any> {
        val group = productOptionCommandService.updateProductOptionGroup(optionGroupId, request)
        return ResponseEntity.ok(mapOf("optionGroupId" to group.id))
    }

    @DeleteMapping("/{optionGroupId}")
    fun deleteOptionGroup(@PathVariable optionGroupId: String): ResponseEntity<Void> {
        productOptionCommandService.deleteProductOptionGroup(optionGroupId)
        return ResponseEntity.noContent().build()
    }
}