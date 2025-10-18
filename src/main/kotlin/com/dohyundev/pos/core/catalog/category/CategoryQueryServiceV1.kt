package com.dohyundev.pos.core.catalog.category

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CategoryQueryServiceV1(
    private val categoryRepository: CategoryRepository
) {

}