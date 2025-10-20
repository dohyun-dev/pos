package com.dohyundev.pos.core.catalog.option_group

import jakarta.persistence.EntityNotFoundException
import jakarta.validation.Valid
import jakarta.validation.constraints.*
import java.math.BigDecimal

interface OptionGroupAggregateCommand {

    data class UpsertOptionGroupCommand(
        val id: Long? = null,

        @field:NotBlank(message = "옵션 그룹 이름은 필수입니다.")
        @field:Size(max = 100)
        val title: String?,

        @field:Size(max = 500)
        val description: String? = null,

        @field:NotNull
        val isRequired: Boolean? = null,

        @field:Min(0)
        val minChoiceCount: Int = 0,

        @field:Min(1)
        val maxChoiceCount: Int = 1,

        val defaultSelectIds: List<Long> = emptyList(),

        @field:NotEmpty
        @field:Valid
        val options: List<UpsertOptionCommand> = emptyList(),

        val isActive: Boolean? = true
    ) {
        fun toNewEntity(): OptionGroup {
            val group = OptionGroup(
                title = title!!,
                description = description,
                isRequired = isRequired!!,
                minChoiceCount = minChoiceCount,
                maxChoiceCount = maxChoiceCount
            )

            val opts = options.mapIndexed { i, o -> o.toOption(group, i.toLong()) }
            group.changeOptions(opts)
            return group
        }

        fun applyTo(
            optionGroup: OptionGroup,
            optionRepository: OptionRepository
        ) {
            val existingOptions = optionGroup.options.associateBy { it.id }
            val updatedOptions = mutableListOf<Option>()
            val idMapping = mutableMapOf<Long, Long>()

            options.forEachIndexed { index, optCmd ->
                val option = if (optCmd.id != null && optCmd.id!! > 0) {
                    val existing = existingOptions[optCmd.id]
                        ?: throw EntityNotFoundException("옵션을 찾을 수 없습니다. ID: ${optCmd.id}")
                    existing.apply {
                        title = optCmd.title!!
                        description = optCmd.description
                        extraPrice = optCmd.extraPrice ?: BigDecimal.ZERO
                        displayOrder = index.toLong()
                    }
                } else {
                    val newOption = optCmd.toOption(optionGroup, index.toLong())
                    val saved = optionRepository.save(newOption)
                    idMapping[optCmd.id ?: (-1L * (index + 1))] = saved.id!!
                    saved
                }
                updatedOptions.add(option)
            }

            val resolvedDefaultSelectedIds = defaultSelectIds.mapNotNull { id ->
                when {
                    id > 0 -> id
                    idMapping.containsKey(id) -> idMapping[id]!!
                    else -> null
                }
            }

            optionGroup.update(
                title = title!!,
                description = description,
                isRequired = isRequired!!,
                minChoiceCount = minChoiceCount,
                maxChoiceCount = maxChoiceCount,
                defaultSelectedIds = resolvedDefaultSelectedIds,
                options = updatedOptions,
                isActive = isActive ?: true
            )
        }
    }

    data class UpsertOptionCommand(
        val id: Long? = null,
        @field:NotBlank @field:Size(max = 100) val title: String?,
        @field:Size(max = 500) val description: String? = null,
        @field:DecimalMin("0") val extraPrice: BigDecimal? = BigDecimal.ZERO,
        val displayOrder: Long? = 0
    ) {
        fun toOption(group: OptionGroup, order: Long): Option {
            return Option(
                group = group,
                title = title!!,
                description = description,
                extraPrice = extraPrice ?: BigDecimal.ZERO,
                displayOrder = order
            )
        }
    }

    data class BulkUpsertOptionGroupCommand(
        @field:NotEmpty @field:Valid
        val commands: List<UpsertOptionGroupCommand> = emptyList()
    )
}
