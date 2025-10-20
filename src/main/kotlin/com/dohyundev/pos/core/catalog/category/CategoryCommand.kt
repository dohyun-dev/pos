package com.dohyundev.pos.core.catalog.category

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

sealed interface CategoryCommand {
    data class CreateCategory(
        @field:NotBlank(message = "카테고리명은 필수입니다.")
        @field:Size(max = 100, message = "카테고리명은 100자를 초과할 수 없습니다.")
        val title: String?,
        
        @field:Size(max = 500, message = "설명은 500자를 초과할 수 없습니다.")
        val description: String? = null
    ) {
        fun toCategory(displayOrder: Long): Category {
            return Category(
                title = title!!,
                description = description,
                displayOrder = displayOrder
            )
        }
    }

    data class UpdateCategory(
        @field:NotBlank(message = "카테고리 ID는 필수입니다.")
        val id: Long?,

        @field:NotBlank(message = "카테고리명은 필수입니다.")
        @field:Size(max = 100, message = "카테고리명은 100자를 초과할 수 없습니다.")
        val title: String?,
        
        @field:Size(max = 500, message = "설명은 500자를 초과할 수 없습니다.")
        val description: String? = null,

        @field:NotNull(message = "표시 순서는 필수입니다.")
        val displayOrder: Long?,

        @field:NotNull(message = "활성화 여부는 필수입니다.")
        val isActive: Boolean? = null
    )

    data class BulkUpdate(
        @field:Valid
        val commands: List<UpdateCategory> = emptyList()
    )
}
