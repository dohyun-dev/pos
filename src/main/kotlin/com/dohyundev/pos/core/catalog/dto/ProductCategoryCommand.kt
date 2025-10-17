package com.dohyundev.pos.core.catalog.dto

import com.dohyundev.pos.core.catalog.domain.entity.ProductCategory
import jakarta.validation.constraints.NotBlank

sealed interface ProductCategoryCommand {
    data class CreateProductCategory(
        @field:NotBlank(message = "카테고리명은 필수입니다.")
        val name: String? = null,
    ) : ProductCategoryCommand {
        fun toEntity(): ProductCategory = ProductCategory(
            name = name!!,
        )
    }

    data class UpdateProductCategory(
        @field:NotBlank(message = "카테고리명은 필수입니다.")
        val name: String? = null,
    ) : ProductCategoryCommand

    data class UpdateDisplayOrder(
        val categoryIds: List<@NotBlank(message = "카데고리 ID는 필수입니다.") String> = emptyList(),
    )
}
