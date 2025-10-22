package com.dohyundev.pos.core.catalog.category

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

sealed interface CategoryCommand {
    data class Create(
        @field:NotBlank(message = "카테고리명은 필수입니다.")
        @field:Size(max = 20, message = "20자 이내로 작성해주세요.")
        val title: String?,

        @field:Size(max = 100, message = "카테고리 설명은 100자 이내로 작성해주세요.")
        val description: String? = null
    )

    data class Update(
        val id: String?,

        @field:NotBlank(message = "카테고리명은 필수입니다.")
        @field:Size(max = 20, message = "20자 이내로 작성해주세요.")
        val title: String?,

        @field:Size(max = 100, message = "카테고리 설명은 100자 이내로 작성해주세요.")
        val description: String? = null,

        @field:NotNull(message = "표시 순서는 필수입니다.")
        val displayOrder: Long?,

        @field:NotNull(message = "활성화 여부는 필수입니다.")
        val isActive: Boolean? = null
    )


    data class BulkUpdate(
        @field:Valid
        val categories: List<Update> = emptyList()
    )
}
