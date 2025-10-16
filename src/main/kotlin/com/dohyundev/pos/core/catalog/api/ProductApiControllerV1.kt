package com.dohyundev.pos.core.catalog.api

import com.dohyundev.pos.core.catalog.application.ProductOptionCommandServiceV1
import com.dohyundev.pos.core.catalog.application.command.ProductCommandServiceV1
import com.dohyundev.pos.core.catalog.dto.ProductCommand
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/products")
class ProductApiControllerV1(
    private val productCommandService: ProductCommandServiceV1,
    private val productOptionGroupCommandService: ProductOptionCommandServiceV1,
    private val productOptionCommandService: ProductOptionCommandServiceV1
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

    // ---------------------------------------------------------------------------------------------

    /** 🟢 옵션 그룹 생성 */
    @PostMapping("/{productId}/option-groups")
    fun createOptionGroup(
        @PathVariable productId: String,
        @Valid @RequestBody request: ProductCommand.CreateOptionGroup
    ): ResponseEntity<Any> {
        val group = productOptionCommandService.createOptionGroup(productId, request)
        return ResponseEntity.ok(mapOf("optionGroupId" to group.id))
    }

    /** 🟡 옵션 그룹 수정 (PUT) */
    @PutMapping("/option-groups/{groupId}")
    fun updateOptionGroup(
        @PathVariable groupId: String,
        @Valid @RequestBody request: ProductCommand.UpdateOptionGroup
    ): ResponseEntity<Any> {
        val group = productCommandService.updateOptionGroup(groupId, request)
        return ResponseEntity.ok(mapOf("optionGroupId" to group.id))
    }

    /** 🔴 옵션 그룹 삭제 */
    @DeleteMapping("/option-groups/{groupId}")
    fun deleteOptionGroup(@PathVariable groupId: String): ResponseEntity<Void> {
        productCommandService.deleteOptionGroup(groupId)
        return ResponseEntity.noContent().build()
    }

    // ---------------------------------------------------------------------------------------------

    /** 🟢 옵션 생성 */
    @PostMapping("/option-groups/{groupId}/options")
    fun createOption(
        @PathVariable groupId: String,
        @Valid @RequestBody request: ProductCommand.CreateOption
    ): ResponseEntity<Any> {
        val option = productCommandService.createOption(groupId, request)
        return ResponseEntity.ok(mapOf("optionId" to option.id))
    }

    /** 🟡 옵션 수정 (PUT) */
    @PutMapping("/options/{optionId}")
    fun updateOption(
        @PathVariable optionId: String,
        @Valid @RequestBody request: ProductCommand.UpdateOption
    ): ResponseEntity<Any> {
        val option = productCommandService.updateOption(optionId, request)
        return ResponseEntity.ok(mapOf("optionId" to option.id))
    }

    /** 🔴 옵션 삭제 */
    @DeleteMapping("/options/{optionId}")
    fun deleteOption(@PathVariable optionId: String): ResponseEntity<Void> {
        productCommandService.deleteOption(optionId)
        return ResponseEntity.noContent().build()
    }
}
