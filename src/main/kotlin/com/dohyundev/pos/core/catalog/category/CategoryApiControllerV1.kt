package com.dohyundev.pos.core.catalog.category

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/catalog/categories")
class CategoryApiControllerV1(
    private val categoryCommandService: CategoryCommandServiceV1,
    private val categoryQueryService: CategoryQueryServiceV1
) {
    @GetMapping
    fun getCategories(): ResponseEntity<List<Any>> {
        // TODO
        return ResponseEntity.ok().build()
    }

    @PostMapping
    fun createCategory(
        @Valid @RequestBody request: CategoryCommand.CreateCategory
    ): ResponseEntity<Any> {
        categoryCommandService.createCategory(request)
        return ResponseEntity.ok().build()
    }

    @PutMapping
    fun updateCategory(
        @PathVariable categoryId: String,
        @Valid @RequestBody request: CategoryCommand.UpdateCategory
    ): ResponseEntity<Any> {
        categoryCommandService.updateCategory(categoryId, request)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/bulk")
    fun bulkUpdateCategories(
        @Valid @RequestBody request: CategoryCommand.BulkUpdate
    ): ResponseEntity<Any> {
        categoryCommandService.bulkUpdateCategories(command = request)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{categoryId}")
    fun deleteCategory(@PathVariable categoryId: String): ResponseEntity<Void> {
        categoryCommandService.deleteCategory(categoryId)
        return ResponseEntity.ok().build()
    }
}
