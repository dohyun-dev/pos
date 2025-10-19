package com.dohyundev.pos.core.catalog.option_group

import jakarta.validation.Valid
import jakarta.validation.constraints.*
import java.math.BigDecimal

interface OptionGroupCommand {
    data class CreateProductOptionGroup(
        @field:NotBlank(message = "옵션 그룹 이름은 필수입니다.")
        @field:Size(max = 100, message = "옵션 그룹 이름은 100자를 초과할 수 없습니다.")
        val name: String?,
        
        @field:Size(max = 500, message = "설명은 500자를 초과할 수 없습니다.")
        val description: String? = null,
        
        val isRequired: Boolean? = null,
        
        @field:Min(value = 1, message = "선택 가능한 옵션 개수는 1 이상이어야 합니다.")
        val selectableOptionCount: Int? = null,
        
        @field:NotEmpty(message = "옵션은 최소 1개 이상 등록해야 합니다.")
        @field:Valid
        val options: List<CreateProductOption>?
    )

    data class CreateProductOption(
        @field:NotBlank(message = "옵션 이름은 필수입니다.")
        @field:Size(max = 100, message = "옵션 이름은 100자를 초과할 수 없습니다.")
        val name: String?,
        
        @field:Size(max = 500, message = "설명은 500자를 초과할 수 없습니다.")
        val description: String? = null,
        
        @field:DecimalMin(value = "0", message = "추가 가격은 0 이상이어야 합니다.")
        val extraPrice: BigDecimal? = null
    )

    data class UpdateOptionGroup(
        @field:NotBlank(message = "옵션 그룹 ID는 필수입니다.")
        val optionGroupId: String?,
        
        @field:NotBlank(message = "옵션 그룹 이름은 필수입니다.")
        @field:Size(max = 100, message = "옵션 그룹 이름은 100자를 초과할 수 없습니다.")
        val name: String?,
        
        @field:Size(max = 500, message = "설명은 500자를 초과할 수 없습니다.")
        val description: String? = null,
        
        val isRequired: Boolean? = null,
        
        @field:Min(value = 1, message = "선택 가능한 옵션 개수는 1 이상이어야 합니다.")
        val selectableOptionCount: Int? = null,
        
        val isActive: Boolean? = null,
        
        @field:NotEmpty(message = "옵션은 최소 1개 이상 등록해야 합니다.")
        @field:Valid
        val options: List<UpdateProductOption>?
    )
    
    data class UpdateProductOption(
        val optionId: String? = null,
        
        @field:NotBlank(message = "옵션 이름은 필수입니다.")
        @field:Size(max = 100, message = "옵션 이름은 100자를 초과할 수 없습니다.")
        val name: String?,
        
        @field:Size(max = 500, message = "설명은 500자를 초과할 수 없습니다.")
        val description: String? = null,
        
        @field:DecimalMin(value = "0", message = "추가 가격은 0 이상이어야 합니다.")
        val extraPrice: BigDecimal? = null
    )

    data class BulkUpdateOptionGroup(
        @field:NotEmpty(message = "업데이트할 옵션 그룹이 없습니다.")
        @field:Valid
        val commands: List<BulkUpdateItem>?
    )
    
    data class BulkUpdateItem(
        @field:NotBlank(message = "옵션 그룹 ID는 필수입니다.")
        val optionGroupId: String?,
        
        @field:NotBlank(message = "옵션 그룹 이름은 필수입니다.")
        @field:Size(max = 100, message = "옵션 그룹 이름은 100자를 초과할 수 없습니다.")
        val name: String?,
        
        @field:Size(max = 500, message = "설명은 500자를 초과할 수 없습니다.")
        val description: String? = null,
        
        @field:NotNull(message = "표시 순서는 필수입니다.")
        val displayOrder: Long?,
        
        val isActive: Boolean? = null
    )
}
