package com.dohyundev.pos.api.catalog.category

import com.dohyundev.pos.core.catalog.category.command.application.CategoryCommand
import com.dohyundev.pos.core.catalog.category.command.application.CategoryCommandServiceV1
import com.dohyundev.pos.core.catalog.category.dto.CategoryDto
import com.dohyundev.pos.core.catalog.category.query.CategoryQueryServiceV1
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/pos/v1/catalog/categories")
class CategoryApiControllerV1(
    private val categoryCommandService: CategoryCommandServiceV1,
    private val categoryQueryService: CategoryQueryServiceV1
) {
    @GetMapping
    fun getCategories(): ResponseEntity<List<CategoryDto>> {
        val categories = categoryQueryService.getCategories()
        return ResponseEntity.ok(categories)
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
