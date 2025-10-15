package com.dohyundev.pos.core.catalog.dto

import com.dohyundev.pos.core.catalog.domain.entity.ProductCategory
import jakarta.validation.constraints.NotBlank

sealed interface ProductCategoryCommand {
    data class CreateProductCategory(
        @field:NotBlank(message = "카테고리명은 필수입니다.")
        val name: String? = null,
        
        val description: String? = null,
        
        val parentId: String? = null,
        
        val isSummary: Boolean = false
    ) : ProductCategoryCommand {
        fun toEntity(parent: ProductCategory? = null): ProductCategory = ProductCategory(
            name = name!!,
            description = description,
            parent = parent,
            isSummary = isSummary
        )
    }

    data class UpdateProductCategory(
        @field:NotBlank(message = "카테고리명은 필수입니다.")
        val name: String? = null,
        
        val description: String? = null,
        
        val parentId: String? = null,
        
        val isSummary: Boolean = false
    ) : ProductCategoryCommand
}
