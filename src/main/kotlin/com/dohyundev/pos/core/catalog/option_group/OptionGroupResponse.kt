package com.dohyundev.pos.core.catalog.option_group

import java.math.BigDecimal
import java.time.LocalDateTime

interface OptionGroupResponse {
    data class Detail(
        val id: String,
        val name: String,
        val description: String?,
        val isRequired: Boolean,
        val selectableOptionCount: Int,
        val displayOrder: Long,
        val isActive: Boolean,
        val options: List<OptionDetail>,
        val createdAt: LocalDateTime?,
        val modifiedAt: LocalDateTime?
    ) {
        companion object {
            fun from(optionGroup: OptionGroup): Detail {
                return Detail(
                    id = optionGroup.id!!,
                    name = optionGroup.name,
                    description = optionGroup.description,
                    isRequired = optionGroup.isRequired,
                    selectableOptionCount = optionGroup.selectableOptionCount,
                    displayOrder = optionGroup.displayOrder,
                    isActive = optionGroup.isActive,
                    options = optionGroup.options
                        .sortedBy { it.displayOrder }
                        .map { OptionDetail.from(it) },
                    createdAt = optionGroup.createdAt,
                    modifiedAt = optionGroup.modifiedAt
                )
            }
        }
    }

    data class Summary(
        val id: String,
        val name: String,
        val description: String?,
        val isRequired: Boolean,
        val selectableOptionCount: Int,
        val displayOrder: Long,
        val isActive: Boolean,
        val optionCount: Int
    ) {
        companion object {
            fun from(optionGroup: OptionGroup): Summary {
                return Summary(
                    id = optionGroup.id!!,
                    name = optionGroup.name,
                    description = optionGroup.description,
                    isRequired = optionGroup.isRequired,
                    selectableOptionCount = optionGroup.selectableOptionCount,
                    displayOrder = optionGroup.displayOrder,
                    isActive = optionGroup.isActive,
                    optionCount = optionGroup.options.size
                )
            }
        }
    }
    
    data class OptionDetail(
        val id: String,
        val name: String,
        val description: String?,
        val extraPrice: BigDecimal,
        val displayOrder: Long
    ) {
        companion object {
            fun from(option: Option): OptionDetail {
                return OptionDetail(
                    id = option.id!!,
                    name = option.name,
                    description = option.description,
                    extraPrice = option.extraPrice,
                    displayOrder = option.displayOrder
                )
            }
        }
    }
}
