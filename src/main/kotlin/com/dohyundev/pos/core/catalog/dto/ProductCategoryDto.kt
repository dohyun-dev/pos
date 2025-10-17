package com.dohyundev.pos.core.catalog.dto

import com.dohyundev.pos.core.catalog.domain.entity.ProductCategory
import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.dohyundev.pos.core.catalog.domain.entity.ProductCategory}
 */
data class ProductCategoryDto(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val displayOrder: Long = 0,
    val isActive: Boolean = true,
    val createdAt: LocalDateTime? = null,
    val modifiedAt: LocalDateTime? = null,
    val createdBy: String? = null,
    val modifiedBy: String? = null,
) : Serializable {
    companion object {
        fun from(productCategory: ProductCategory): ProductCategoryDto {
            return ProductCategoryDto(
                id = productCategory.id,
                name = productCategory.name,
                description = productCategory.description,
                displayOrder = productCategory.displayOrder,
                isActive = productCategory.isActive,
                createdAt = productCategory.createdAt,
                modifiedAt = productCategory.modifiedAt,
                createdBy = productCategory.createdBy,
                modifiedBy = productCategory.modifiedBy
            )
        }
    }
}