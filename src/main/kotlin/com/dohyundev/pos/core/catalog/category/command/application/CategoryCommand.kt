package com.dohyundev.pos.core.catalog.category.command.application

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

sealed interface CategoryCommand {
    data class CreateCategory(
        @field:NotBlank(message = "카테고리명은 필수입니다.")
        @field:Size(max = 20, message = "20자 이내로 입력해주세요.")
        val name: String?,
    ) {
    }

    data class BulkUpdate(
        @field:NotEmpty(message = "업데이트할 카테고리가 없습니다.")
        @field:Valid
        val categories: List<BulkUpdateItem>?
    )

    data class UpdateCategory(
        @field:NotBlank(message = "카테고리명은 필수입니다.")
        @field:Size(max = 20, message = "20자 이내로 입력해주세요.")
        val name: String?,

        @field:NotNull(message = "표시 순서는 필수입니다.")
        val displayOrder: Int?,

        val isActive: Boolean? = null
    )
    
    data class BulkUpdateItem(
        val id: Long?,

        @field:NotBlank(message = "카테고리명은 필수입니다.")
        @field:Size(max = 20, message = "20자 이내로 입력해주세요.")
        val name: String?,
        
        @field:NotNull(message = "표시 순서는 필수입니다.")
        val displayOrder: Int?,
        
        val isActive: Boolean? = null
    )
}
