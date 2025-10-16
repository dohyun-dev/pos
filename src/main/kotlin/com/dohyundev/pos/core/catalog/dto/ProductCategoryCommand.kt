package com.dohyundev.pos.core.catalog.dto

import com.dohyundev.common.vo.MoveDirection
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

    data class MoveProductCategory(
        @field:NotBlank(message = "카데고리 ID는 필수입니다.")
        val categoryId: String?,
        @field:NotBlank(message = "이동할 방향은 필수입니다.")
        val direction: MoveDirection?
    )


}
