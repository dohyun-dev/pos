package com.dohyundev.pos.core.catalog.category.query

import com.dohyundev.pos.core.catalog.category.command.domain.CategoryRepository
import com.dohyundev.pos.core.catalog.category.dto.CategoryDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CategoryQueryServiceV1(
    private val categoryRepository: CategoryRepository
) {
    fun getCategories(): List<CategoryDto> {
        return categoryRepository.findAll()
            .sortedBy { it.displayOrder }
            .map { CategoryDto.from(it) }
    }
}
