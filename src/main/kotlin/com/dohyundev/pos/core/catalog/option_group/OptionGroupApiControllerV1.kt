package com.dohyundev.pos.core.catalog.option_group

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/catalog/options-groups")
class OptionGroupApiControllerV1(
    private val optionQueryService: OptionQueryServiceV1,
    private val optionCommandService: OptionGroupCommandServiceV1
) {
    @GetMapping
    fun getCategories(): ResponseEntity<List<Any>> {
        // TODO
        return ResponseEntity.ok().build()
    }

    @PostMapping("/aggregate")
    fun createOptionGroupAggregate(
        @Valid @RequestBody request: OptionGroupCommand.CreateProductOptionGroup
    ): ResponseEntity<Any> {
        optionCommandService.createOptionGroup(request)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/aggregate")
    fun updateOptionGroupAggregate(
        @Valid @RequestBody request: OptionGroupCommand.UpdateOptionGroup
    ): ResponseEntity<Any> {
        optionCommandService.updateOptionGroup(request)
        return ResponseEntity.ok().build()
    }

    @PutMapping
    fun bulkUpdateOptionGroup(
        @Valid @RequestBody request: OptionGroupCommand.BulkUpdateOptionGroup
    ): ResponseEntity<Any> {
        optionCommandService.bulkUpdateOptionGroup(request)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{optionGroupId}")
    fun deleteOptionGroup(@PathVariable optionGroupId: String): ResponseEntity<Void> {
        optionCommandService.deleteOptionGroup(optionGroupId)
        return ResponseEntity.ok().build()
    }
}