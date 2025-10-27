package com.dohyundev.pos.core.catalog.category.command.application

import com.dohyundev.pos.core.catalog.category.command.domain.Category
import com.dohyundev.pos.core.catalog.category.dto.CategoryDto
import com.dohyundev.pos.core.catalog.category.command.domain.CategoryRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryCommandServiceV1(
    private val categoryRepository: CategoryRepository
) {
    @Transactional
    fun createCategory(command: CategoryCommand.CreateCategory): CategoryDto {
        val maxDisplayOrder = categoryRepository.findMaxDisplayOrder()

        val newCategory = Category(
            name = command.name!!,
            displayOrder = maxDisplayOrder + 1
        )

        return CategoryDto.from(categoryRepository.save(newCategory))
    }

    @Transactional
    fun bulkUpdateCategories(command: CategoryCommand.BulkUpdate): List<CategoryDto> {
        return command.categories!!
            .filter { it.id != null }
            .map { item ->
                updateCategory(
                    id = item.id!!,
                    command = CategoryCommand.UpdateCategory(
                        name = item.name,
                        displayOrder = item.displayOrder,
                        isActive = item.isActive
                    )
                )
            }
    }

    @Transactional
    fun updateCategory(id: Long, command: CategoryCommand.UpdateCategory): CategoryDto {
        val category = categoryRepository.findByIdOrNull(id)
            ?: throw EntityNotFoundException("카테고리를 찾을 수 없습니다. ID: ${id}")

        category.update(
            name = command.name!!,
            displayOrder = command.displayOrder!!,
            isActive = command.isActive!!
        )

        return CategoryDto.from(category)
    }

    @Transactional
    fun deleteCategory(categoryId: Long) {
        val category = categoryRepository.findByIdOrNull(categoryId)
            ?: throw EntityNotFoundException("카테고리를 찾을 수 없습니다. ID: $categoryId")
        
        categoryRepository.delete(category)
    }
}
