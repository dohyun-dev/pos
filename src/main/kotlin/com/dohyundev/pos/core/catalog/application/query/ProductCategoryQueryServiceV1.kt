package com.dohyundev.pos.core.catalog.application.query

import com.dohyundev.pos.core.catalog.domain.repository.ProductCategoryRepository
import com.dohyundev.pos.core.catalog.dto.ProductCategoryDto
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductCategoryQueryServiceV1(
    private val productCategoryRepository: ProductCategoryRepository
) {
    fun findAll(): List<ProductCategoryDto> = productCategoryRepository.findAll(
        Sort.by(Sort.Direction.ASC, "displayOrder")
    ).map {
        ProductCategoryDto.from(it)
    }
}