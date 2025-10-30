package com.dohyundev.pos.core.catalog.option_group.command.dto

import com.dohyundev.pos.core.catalog.option_group.command.domain.Option
import com.dohyundev.pos.core.catalog.option_group.command.domain.OptionState
import java.io.Serializable

/**
 * DTO for {@link com.dohyundev.pos.core.catalog.option_group.command.domain.Option}
 */
data class OptionDto(
    val optionGroupId: Long? = null,
    val name: String? = null,
    val price: Long? = null,
    val isDefault: Boolean = false,
    val state: OptionState = OptionState.ON_SALE,
    val displayOrder: Int? = null
) : Serializable {
    companion object {
        fun from(option: Option): OptionDto {
            return OptionDto(
                optionGroupId = option.id,
                name = option.name,
                price = option.price,
                isDefault = option.isDefault,
                state = option.state,
                displayOrder = option.displayOrder,
            )
        }
    }
}