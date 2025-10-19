package com.dohyundev.pos.core.catalog.category

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

sealed interface CategoryCommand {
    data class CreateCategory(
        @field:NotBlank(message = "카테고리명은 필수입니다.")
        @field:Size(max = 100, message = "카테고리명은 100자를 초과할 수 없습니다.")
        val name: String,
        
        @field:Size(max = 500, message = "설명은 500자를 초과할 수 없습니다.")
        val description: String? = null
    )

    data class UpdateCategory(
        @field:NotBlank(message = "카테고리명은 필수입니다.")
        @field:Size(max = 100, message = "카테고리명은 100자를 초과할 수 없습니다.")
        val name: String,
        
        @field:Size(max = 500, message = "설명은 500자를 초과할 수 없습니다.")
        val description: String? = null,
        
        val isActive: Boolean? = null
    )

    data class BulkUpdate(
        @field:NotEmpty(message = "업데이트할 카테고리가 없습니다.")
        @field:Valid
        val commands: List<BulkUpdateItem>
    )
    
    data class BulkUpdateItem(
        @field:NotBlank(message = "카테고리 ID는 필수입니다.")
        val categoryId: String,
        
        @field:NotBlank(message = "카테고리명은 필수입니다.")
        @field:Size(max = 100, message = "카테고리명은 100자를 초과할 수 없습니다.")
        val name: String,
        
        @field:Size(max = 500, message = "설명은 500자를 초과할 수 없습니다.")
        val description: String? = null,
        
        @field:NotNull(message = "표시 순서는 필수입니다.")
        val displayOrder: Long,
        
        val isActive: Boolean? = null
    )
}
