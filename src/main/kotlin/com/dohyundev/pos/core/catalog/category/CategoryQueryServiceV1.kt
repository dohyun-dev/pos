package com.dohyundev.pos.core.catalog.category

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CategoryQueryServiceV1(
    private val categoryRepository: CategoryRepository
) {
    fun getCategories(): List<CategoryResponse.Summary> {
        return categoryRepository.findAll()
            .sortedBy { it.displayOrder }
            .map { CategoryResponse.Summary.from(it) }
    }

    fun getCategory(categoryId: Long): CategoryResponse.Detail {
        val category = categoryRepository.findByIdOrNull(categoryId)
            ?: throw EntityNotFoundException("카테고리를 찾을 수 없습니다. ID: $categoryId")
        
        return CategoryResponse.Detail.from(category)
    }
}
