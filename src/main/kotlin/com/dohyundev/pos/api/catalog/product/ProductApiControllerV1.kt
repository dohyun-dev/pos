package com.dohyundev.pos.api.catalog.product

import com.dohyundev.pos.core.catalog.option_group.command.application.OptionGroupCommandServiceV1
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pos/v1/catalog/products")
class ProductApiControllerV1(
    private val optionGroupCommandService: OptionGroupCommandServiceV1
) {
    @GetMapping
    fun getOptionGroups(): ResponseEntity<Any> {
        //TODO
        return ResponseEntity.ok().build()
    }

    @PostMapping("/aggregate")
    fun createOptionGroupAggregate(): ResponseEntity<Any> {
        //TODO
        return ResponseEntity.ok().build()
    }

    @PutMapping("/aggregate")
    fun updateOptionGroupAggregate(): ResponseEntity<Any> {
        //TODO
        return ResponseEntity.ok().build()
    }

    @PutMapping("/bulk")
    fun bulkUpdateOptionGroup(): ResponseEntity<Any> {
        //TODO
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/option-groups/{optionGroupId}")
    fun deleteOptionGroup(
        @PathVariable optionGroupId: Long,
    ): ResponseEntity<Any> {
        //TODO
        return ResponseEntity.ok().build()
    }
}