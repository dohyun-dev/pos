package com.dohyundev.pos.core.catalog.category

import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.dohyundev.pos.core.catalog.category.Category}
 */
data class CategoryDto(
    val id: Long? = null,
    val title: String? = null,
    val description: String? = null,
    val displayOrder: Long = 0,
    val isActive: Boolean = true,
    val createdAt: LocalDateTime? = null,
    val modifiedAt: LocalDateTime? = null,
    val createdBy: String? = null,
    val modifiedBy: String? = null,
    val version: Long? = null,
) : Serializable {
    companion object {
        fun from(category: Category): CategoryDto {
            return CategoryDto(
                id = category.id,
                title = category.title,
                description = category.description,
                displayOrder = category.displayOrder,
                isActive = category.isActive,
                createdAt = category.createdAt,
                modifiedAt = category.modifiedAt,
                createdBy = category.createdBy,
                modifiedBy = category.modifiedBy,
            )
        }
    }
}