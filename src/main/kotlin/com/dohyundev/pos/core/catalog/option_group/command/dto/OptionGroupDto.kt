package com.dohyundev.pos.core.catalog.option_group.command.dto

import com.dohyundev.pos.core.catalog.option_group.command.domain.OptionGroup
import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.dohyundev.pos.core.catalog.option_group.command.domain.OptionGroup}
 */
data class OptionGroupDto(
    val id: Long? = null,
    val name: String? = null,
    val isRequired: Boolean = false,
    val choices: List<OptionDto> = emptyList(),
    val minChoices: Int = 0,
    val maxChoices: Int = 1,
    val displayOrder: Int? = null,
    val createdAt: LocalDateTime? = null,
    val modifiedAt: LocalDateTime? = null,
    val createdBy: Long? = null,
    val modifiedBy: Long? = null,
) : Serializable {
    companion object {
        fun from(optionGroup: OptionGroup): OptionGroupDto {
            return OptionGroupDto(
                id = optionGroup.id,
                name = optionGroup.name,
                isRequired = optionGroup.isRequired,
                choices = optionGroup.choices.map { OptionDto.from(it) },
                minChoices = optionGroup.minChoices,
                maxChoices = optionGroup.maxChoices,
                displayOrder = optionGroup.displayOrder,
                createdAt = optionGroup.createdAt,
                modifiedAt = optionGroup.modifiedAt,
                createdBy = optionGroup.createdBy,
                modifiedBy = optionGroup.modifiedBy
            )
        }
    }
}