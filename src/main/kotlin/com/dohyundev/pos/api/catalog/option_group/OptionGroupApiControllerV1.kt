package com.dohyundev.pos.api.catalog.option_group

import com.dohyundev.pos.core.catalog.option_group.command.OptionGroupQueryServiceV1
import com.dohyundev.pos.core.catalog.option_group.command.application.OptionGroupCommand
import com.dohyundev.pos.core.catalog.option_group.command.application.OptionGroupCommandServiceV1
import com.dohyundev.pos.core.catalog.option_group.command.dto.OptionGroupDto
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pos/v1/catalog/option-groups")
class OptionGroupApiControllerV1(
    private val optionGroupCommandService: OptionGroupCommandServiceV1,
    private val optionGroupQueryService: OptionGroupQueryServiceV1
) {
    @GetMapping
    fun getOptionGroups(): ResponseEntity<List<OptionGroupDto>> {
        val optionGroups = optionGroupQueryService.getOptionGroups()
        return ResponseEntity.ok(optionGroups)
    }

    @PostMapping
    fun createOptionGroup(
        @Valid @RequestBody request: OptionGroupCommand.CreateOptionGroup
    ): ResponseEntity<OptionGroupDto> {
        val created = optionGroupCommandService.createOptionGroup(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }

    @PutMapping("/{optionGroupId}")
    fun updateOptionGroup(
        @PathVariable optionGroupId: Long,
        @Valid @RequestBody request: OptionGroupCommand.UpdateOptionGroup
    ): ResponseEntity<OptionGroupDto> {
        val updated = optionGroupCommandService.updateOptionGroup(optionGroupId, request)
        return ResponseEntity.ok(updated)
    }

    @PutMapping("/bulk")
    fun bulkUpdateOptionGroups(
        @Valid @RequestBody request: OptionGroupCommand.BulkUpdateOptionGroups
    ): ResponseEntity<List<OptionGroupDto>> {
        val updated = optionGroupCommandService.bulkUpdateOptionGroups(request)
        return ResponseEntity.ok(updated)
    }

    @DeleteMapping("/{optionGroupId}")
    fun deleteOptionGroup(
        @PathVariable optionGroupId: Long
    ): ResponseEntity<Void> {
        optionGroupCommandService.deleteOptionGroup(optionGroupId)
        return ResponseEntity.noContent().build()
    }
}
