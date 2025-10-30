package com.dohyundev.pos.core.catalog.option_group.command.application

import com.dohyundev.pos.core.catalog.option_group.command.domain.OptionState
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero

sealed interface OptionGroupCommand {
    open class UpsertOptionGroup(
        @field:NotBlank(message = "옵션 그룹명은 필수입니다.")
        val name: String?,

        @field:NotNull(message = "필수 여부는 필수입니다.")
        val isRequired: Boolean?,

        @field:NotEmpty(message = "옵션은 최소 1개 이상 필요합니다.")
        @field:Valid
        val choices: List<UpsertOption>?,

        @field:NotNull(message = "최소 선택 개수는 필수입니다.")
        @field:PositiveOrZero(message = "최소 선택 개수는 0 이상이어야 합니다.")
        val minChoices: Int?,

        @field:NotNull(message = "최대 선택 개수는 필수입니다.")
        @field:PositiveOrZero(message = "최대 선택 개수는 0 이상이어야 합니다.")
        val maxChoices: Int?,

        @field:NotNull(message = "표시 순서는 필수입니다.")
        val displayOrder: Int?
    )
    
    data class UpsertOption(
        @field:NotBlank(message = "옵션명은 필수입니다.")
        val name: String?,
        
        @field:NotNull(message = "가격은 필수입니다.")
        val price: Long?,

        @field:NotNull(message = "기본값 여부는 필수입니다.")
        val isDefault: Boolean?,

        @field:NotNull(message = "옵션 상태는 필수입니다.")
        val state: OptionState?,
        
        @field:NotNull(message = "표시 순서는 필수입니다.")
        val displayOrder: Int?
    )
    
    data class BulkUpdateOptionGroups(
        @field:NotEmpty(message = "업데이트할 옵션 그룹이 없습니다.")
        @field:Valid
        val optionGroups: List<BulkUpdateItem>?
    )
    
    class BulkUpdateItem(
        @NotNull(message = "옵션 그룹 ID는 필숭입니다.")
        val id: Long?,
        name: String?,
        isRequired: Boolean?,
        choices: List<UpsertOption>?,
        minChoices: Int?,
        maxChoices: Int?,
        displayOrder: Int?
    ): UpsertOptionGroup(
        name = name,
        isRequired = isRequired,
        choices = choices,
        minChoices = minChoices,
        maxChoices = maxChoices,
        displayOrder = displayOrder
    )
}
