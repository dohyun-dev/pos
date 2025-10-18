package com.dohyundev.pos.core.catalog.category

import jakarta.validation.constraints.NotBlank

sealed interface CategoryCommand {
    data class CreateCategory(
        @field:NotBlank(message = "카테고리명은 필수입니다.")
        val name: String? = null,
    ) {
    }

    data class UpdateCategory(
        @field:NotBlank(message = "카테고리명은 필수입니다.")
        val name: String? = null,
    )


    data class BulkUpdate(
        val commands: List<Any>,
    )
}
