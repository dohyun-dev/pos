package com.dohyundev.pos.core.catalog.category

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/catalog/categories")
class CategoryApiControllerV1(
    private val categoryCommandService: CategoryCommandServiceV1,
    private val categoryQueryService: CategoryQueryServiceV1
) {
    @GetMapping
    fun getAll(): List<CategoryDto>  = categoryQueryService.getCategories()

    @PostMapping
    fun create(@RequestBody command: CategoryCommand.Create): ResponseEntity<CategoryDto> {
        val newCategory = categoryCommandService.create(command)

        return ResponseEntity.ok(CategoryDto.from(newCategory))
    }

    @PutMapping("/bulk")
    fun bulkUpdate(@RequestBody command: CategoryCommand.BulkUpdate): ResponseEntity<List<CategoryDto>> {
        val updatedCategories = categoryCommandService.bulkUpdate(command)
        return ResponseEntity.ok(updatedCategories.map { CategoryDto.from(it) })
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = categoryCommandService.delete(id)
}
