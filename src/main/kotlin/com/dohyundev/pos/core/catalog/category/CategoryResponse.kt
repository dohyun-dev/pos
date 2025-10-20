package com.dohyundev.pos.core.catalog.category

import java.time.LocalDateTime

interface CategoryResponse {
    data class Detail(
        val id: Long,
        val title: String,
        val description: String?,
        val displayOrder: Long,
        val isActive: Boolean,
        val createdAt: LocalDateTime?,
        val modifiedAt: LocalDateTime?
    ) {
        companion object {
            fun from(category: Category): Detail {
                return Detail(
                    id = category.id!!,
                    title = category.title,
                    description = category.description,
                    displayOrder = category.displayOrder,
                    isActive = category.isActive,
                    createdAt = category.createdAt,
                    modifiedAt = category.modifiedAt
                )
            }
        }
    }

    data class Summary(
        val id: Long,
        val title: String,
        val description: String?,
        val displayOrder: Long,
        val isActive: Boolean
    ) {
        companion object {
            fun from(category: Category): Summary {
                return Summary(
                    id = category.id!!,
                    title = category.title,
                    description = category.description,
                    displayOrder = category.displayOrder,
                    isActive = category.isActive
                )
            }
        }
    }
}
