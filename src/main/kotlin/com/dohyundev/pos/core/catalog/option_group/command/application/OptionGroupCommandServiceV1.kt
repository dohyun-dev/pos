package com.dohyundev.pos.core.catalog.option_group.command.application

import com.dohyundev.pos.core.catalog.option_group.command.domain.Option
import com.dohyundev.pos.core.catalog.option_group.command.domain.OptionGroup
import com.dohyundev.pos.core.catalog.option_group.command.domain.OptionGroupRepository
import com.dohyundev.pos.core.catalog.option_group.command.dto.OptionGroupDto
import com.dohyundev.pos.core.catalog.option_group.command.exception.OptionGroupEntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OptionGroupCommandServiceV1(
    private val optionGroupRepository: OptionGroupRepository
) {

    @Transactional
    fun createOptionGroup(command: OptionGroupCommand.CreateOptionGroup): OptionGroupDto {
        val newOptionGroup = OptionGroup(
            name = command.name!!,
            isRequired = command.isRequired!!,
            choices = command.choices!!.map { 
                Option(
                    name = it.name!!,
                    price = it.price!!,
                    isDefault = it.isDefault,
                    state = it.state!!,
                    displayOrder = it.displayOrder!!
                ) 
            },
            minChoices = command.minChoices!!,
            maxChoices = command.maxChoices!!,
            displayOrder = command.displayOrder!!
        )
        return OptionGroupDto.from(optionGroupRepository.save(newOptionGroup))
    }

    @Transactional
    fun updateOptionGroup(optionGroupId: Long, command: OptionGroupCommand.UpdateOptionGroup): OptionGroupDto {
        val optionGroup = (optionGroupRepository.findByIdOrNull(optionGroupId)
            ?: throw OptionGroupEntityNotFoundException())

        optionGroup.update(
            name = command.name!!,
            isRequired = command.isRequired!!,
            choices = command.choices!!.map { 
                Option(
                    name = it.name!!,
                    price = it.price!!,
                    isDefault = it.isDefault,
                    state = it.state!!,
                    displayOrder = it.displayOrder!!
                )
            },
            minChoices = command.minChoices!!,
            maxChoices = command.maxChoices!!,
            displayOrder = command.displayOrder!!
        )

        return OptionGroupDto.from(optionGroup)
    }

    @Transactional
    fun bulkUpdateOptionGroups(command: OptionGroupCommand.BulkUpdateOptionGroups): List<OptionGroupDto> {
        return command.optionGroups!!.map {
            val optionGroup = (optionGroupRepository.findByIdOrNull(it.id!!)
                ?: throw OptionGroupEntityNotFoundException())

            optionGroup.update(
                name = it.name!!,
                isRequired = it.isRequired!!,
                choices = it.choices!!.map { choice ->
                    Option(
                        name = choice.name!!,
                        price = choice.price!!,
                        isDefault = choice.isDefault,
                        state = choice.state!!,
                        displayOrder = choice.displayOrder!!
                    )
                },
                minChoices = it.minChoices!!,
                maxChoices = it.maxChoices!!,
                displayOrder = it.displayOrder!!
            )

            OptionGroupDto.from(optionGroup)
        }
    }

    @Transactional
    fun deleteOptionGroup(optionGroupId: Long) {
        val optionGroup = (optionGroupRepository.findByIdOrNull(optionGroupId)
            ?: throw OptionGroupEntityNotFoundException())
        optionGroupRepository.delete(optionGroup)
    }
}
