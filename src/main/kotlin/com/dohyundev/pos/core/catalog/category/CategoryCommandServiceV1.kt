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
    fun createCategory(command: CategoryCommand.CreateCategory) {
        // TODO
    }

    @Transactional
    fun updateCategory(categoryId: String, command: CategoryCommand.UpdateCategory) {
        // TODO
    }

    @Transactional
    fun bulkUpdateCategories(command: CategoryCommand.BulkUpdate) {
        // TODO
    }

    @Transactional
    fun deleteCategory(categoryId: String) {
        val productCategory = categoryRepository.findByIdOrNull(categoryId) ?: throw EntityNotFoundException()
        categoryRepository.delete(productCategory)
    }
}
