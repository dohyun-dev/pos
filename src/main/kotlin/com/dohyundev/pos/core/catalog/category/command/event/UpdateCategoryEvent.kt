package com.dohyundev.pos.core.catalog.category.command.event

import com.dohyundev.pos.core.catalog.category.command.domain.Category

data class UpdateCategoryEvent(
    val category: Category,
) {
}