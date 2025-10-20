package com.dohyundev.pos.core.catalog.category

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/catalog/categories")
class CategoryApiControllerV1(
    private val categoryCommandService: CategoryCommandServiceV1,
    private val categoryQueryService: CategoryQueryServiceV1
) {
    @GetMapping
    fun getCategories(): ResponseEntity<List<CategoryResponse.Summary>> {
        val categories = categoryQueryService.getCategories()
        return ResponseEntity.ok(categories)
    }

    @GetMapping("/{categoryId}")
    fun getCategory(
        @PathVariable categoryId: Long
    ): ResponseEntity<CategoryResponse.Detail> {
        val category = categoryQueryService.getCategory(categoryId)
        return ResponseEntity.ok(category)
    }

    @PostMapping
    fun createCategory(
        @Valid @RequestBody request: CategoryCommand.CreateCategory
    ): ResponseEntity<Void> {
        val categoryId = categoryCommandService.createCategory(request)
        return ResponseEntity.created(URI.create("/api/v1/catalog/categories/$categoryId")).build()
    }

    @PutMapping("/bulk")
    fun bulkUpdateCategories(
        @Valid @RequestBody request: CategoryCommand.BulkUpdate
    ): ResponseEntity<Void> {
        categoryCommandService.bulkUpdateCategories(command = request)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{categoryId}")
    fun deleteCategory(@PathVariable categoryId: Long): ResponseEntity<Void> {
        categoryCommandService.deleteCategory(categoryId)
        return ResponseEntity.noContent().build()
    }
}
