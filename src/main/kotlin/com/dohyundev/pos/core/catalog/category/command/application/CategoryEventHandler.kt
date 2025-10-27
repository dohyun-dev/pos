package com.dohyundev.pos.core.catalog.category.command.application

import com.dohyundev.pos.core.catalog.category.command.domain.Category
import com.dohyundev.pos.core.catalog.category.command.domain.CategoryRepository
import com.dohyundev.pos.core.catalog.category.command.event.PersistCategoryEvent
import com.dohyundev.pos.core.catalog.category.command.event.UpdateCategoryEvent
import com.dohyundev.pos.core.catalog.category.command.exception.DuplicateCategoryTitleException
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryEventHandler(
    private val categoryRepository: CategoryRepository,
) {
    @Transactional(readOnly = true)
    @EventListener(PersistCategoryEvent::class)
    fun handlePersistCategoryEvent(event: PersistCategoryEvent) {
        validateDuplicateName(event.category)
    }

    @Transactional(readOnly = true)
    @EventListener(UpdateCategoryEvent::class)
    fun handleUpdateCategoryEvent(event: UpdateCategoryEvent) {
        validateDuplicateName(event.category)
    }


    @Transactional(readOnly = true)
    fun validateDuplicateName(category: Category) {
        val existsByIdNotAndName = categoryRepository.existsByIdNotAndName(category.id!!, name = category.name)

        if (existsByIdNotAndName)
            throw DuplicateCategoryTitleException()
    }
}