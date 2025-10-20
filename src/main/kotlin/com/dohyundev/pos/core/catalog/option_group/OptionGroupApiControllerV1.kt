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
        @PathVariable optionGroupId: Long
    ): ResponseEntity<OptionGroupResponse.Detail> {
        val optionGroup = optionQueryService.getOptionGroup(optionGroupId)
        return ResponseEntity.ok(optionGroup)
    }

    @PostMapping
    fun createOptionGroup(
        @Valid @RequestBody request: OptionGroupAggregateCommand.UpsertOptionGroupCommand
    ): ResponseEntity<Void> {
        val optionGroupId = optionCommandService.upsertOptionGroup(request)
        return ResponseEntity.created(URI.create("/api/v1/catalog/option-groups/$optionGroupId")).build()
    }

    @PutMapping
    fun updateOptionGroup(
        @Valid @RequestBody request: OptionGroupAggregateCommand.UpsertOptionGroupCommand
    ): ResponseEntity<Void> {
        optionCommandService.upsertOptionGroup(request)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/bulk")
    fun bulkUpdateOptionGroup(
        @Valid @RequestBody request: OptionGroupAggregateCommand.BulkUpsertOptionGroupCommand
    ): ResponseEntity<Void> {
        optionCommandService.bulkUpsertOptionGroup(request)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{optionGroupId}")
    fun deleteOptionGroup(@PathVariable optionGroupId: Long): ResponseEntity<Void> {
        optionCommandService.deleteOptionGroup(optionGroupId)
        return ResponseEntity.noContent().build()
    }
}
