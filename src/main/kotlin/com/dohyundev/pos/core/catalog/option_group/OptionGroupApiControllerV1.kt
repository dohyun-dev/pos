package com.dohyundev.pos.core.catalog.option_group

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/catalog/option-groups")
class OptionGroupApiControllerV1(
    private val optionQueryService: OptionQueryServiceV1,
    private val optionCommandService: OptionGroupCommandServiceV1
) {
    @GetMapping
    fun getOptionGroups(): ResponseEntity<List<OptionGroupResponse.Summary>> {
        val optionGroups = optionQueryService.getOptionGroups()
        return ResponseEntity.ok(optionGroups)
    }

    @GetMapping("/{optionGroupId}")
    fun getOptionGroup(
        @PathVariable optionGroupId: String
    ): ResponseEntity<OptionGroupResponse.Detail> {
        val optionGroup = optionQueryService.getOptionGroup(optionGroupId)
        return ResponseEntity.ok(optionGroup)
    }

    @PostMapping
    fun createOptionGroup(
        @Valid @RequestBody request: OptionGroupCommand.CreateProductOptionGroup
    ): ResponseEntity<Void> {
        val optionGroupId = optionCommandService.createOptionGroup(request)
        return ResponseEntity.created(URI.create("/api/v1/catalog/option-groups/$optionGroupId")).build()
    }

    @PutMapping
    fun updateOptionGroup(
        @Valid @RequestBody request: OptionGroupCommand.UpdateOptionGroup
    ): ResponseEntity<Void> {
        optionCommandService.updateOptionGroup(request)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/bulk")
    fun bulkUpdateOptionGroup(
        @Valid @RequestBody request: OptionGroupCommand.BulkUpdateOptionGroup
    ): ResponseEntity<Void> {
        optionCommandService.bulkUpdateOptionGroup(request)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{optionGroupId}")
    fun deleteOptionGroup(@PathVariable optionGroupId: String): ResponseEntity<Void> {
        optionCommandService.deleteOptionGroup(optionGroupId)
        return ResponseEntity.noContent().build()
    }
}
