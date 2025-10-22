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
    fun create(command: CategoryCommand.Create): Category {
        if (categoryRepository.existsByTitle(command.title!!))
            throw IllegalArgumentException("중복되는 이름의 카테고리가 있습니다.")
        val maxOrder = categoryRepository.findMaxDisplayOrder()
        val category = Category(title = command.title, displayOrder = maxOrder + 1)
        return categoryRepository.save(category)
    }

    @Transactional
    fun bulkUpdate(command: CategoryCommand.BulkUpdate): List<Category> {
        val updated = mutableListOf<Category>()
        command.categories.forEach {
            val category = categoryRepository.findByIdOrNull(it.id)
                ?: throw EntityNotFoundException("카테고리를 찾을 수 없습니다: ${it.id}")
            category.update(
                title = it.title!!,
                displayOrder = it.displayOrder!!,
                isActive = it.isActive!!,
            )
            updated.add(category)
        }

        return categoryRepository.saveAll(updated)
    }

    @Transactional
    fun delete(categoryId: Long) {
        val category = categoryRepository.findByIdOrNull(categoryId)
            ?: throw EntityNotFoundException("카테고리를 찾을 수 없습니다.")

        categoryRepository.delete(category)
    }
}
