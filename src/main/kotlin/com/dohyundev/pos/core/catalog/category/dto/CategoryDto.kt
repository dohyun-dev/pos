package com.dohyundev.pos.core.catalog.category.dto

import com.dohyundev.pos.core.catalog.category.command.domain.Category
import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.dohyundev.pos.core.catalog.category.command.domain.Category}
 */
data class CategoryDto(
    val id: String? = null,
    val name: String? = null,
    val displayOrder: Int = 0,
    val isActive: Boolean = true,
    val createdAt: LocalDateTime? = null,
    val modifiedAt: LocalDateTime? = null,
    val createdBy: Long? = null,
    val modifiedBy: Long? = null,
) : Serializable {
    companion object {
        fun from(category: Category): CategoryDto {
            return CategoryDto(
                id = category.id!!.toString(),
                name = category.name,
                displayOrder = category.displayOrder,
                isActive = category.active,
                createdAt = category.createdAt,
                modifiedAt = category.modifiedAt,
                createdBy = category.createdBy,
                modifiedBy = category.modifiedBy
            )
        }
    }
}