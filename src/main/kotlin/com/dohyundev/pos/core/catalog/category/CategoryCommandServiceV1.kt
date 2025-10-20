package com.dohyundev.pos.core.catalog.category

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryCommandServiceV1(
    private val categoryRepository: CategoryRepository
) {
    @Transactional
    fun createCategory(command: CategoryCommand.CreateCategory): Long {
        val maxDisplayOrder = categoryRepository.findMaxDisplayOrder()
        val category = command.toCategory(maxDisplayOrder + 1)
        return categoryRepository.save(category).id!!
    }

    @Transactional
    fun bulkUpdateCategories(command: CategoryCommand.BulkUpdate) {
        command.commands.forEach { item ->
            val category = categoryRepository.findByIdOrNull(item.id!!)
                ?: throw EntityNotFoundException("카테고리를 찾을 수 없습니다. ID: ${item.id}")

            category.update(
                title = item.title!!,
                description = item.description,
                displayOrder = item.displayOrder!!,
                isActive = item.isActive!!
            )
        }
    }

    @Transactional
    fun deleteCategory(categoryId: Long) {
        val category = categoryRepository.findByIdOrNull(categoryId)
            ?: throw EntityNotFoundException("카테고리를 찾을 수 없습니다. ID: $categoryId")
        categoryRepository.delete(category)
    }
}
