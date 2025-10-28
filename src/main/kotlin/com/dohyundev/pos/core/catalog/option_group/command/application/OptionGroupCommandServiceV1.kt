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
    fun createOptionGroup(command: OptionGroupDto): OptionGroupDto {
        val newOptionGroup = OptionGroup(
            name = command.name!!,
            isRequired = command.isRequired,
            choices = command.choices.map { Option(
                name = it.name!!,
                price = it.price!!,
                isDefault = it.isDefault,
                state = it.state,
                displayOrder = it.displayOrder!!
            ) },
            minChoices = command.minChoices,
            maxChoices = command.maxChoices,
            displayOrder = command.displayOrder!!
        )
        return OptionGroupDto.from(optionGroupRepository.save(newOptionGroup))
    }

    @Transactional
    fun updateOptionGroup(optionGroupId: Long, command: OptionGroupDto): OptionGroupDto {
        val optionGroup = (optionGroupRepository.findByIdOrNull(optionGroupId)
            ?: throw OptionGroupEntityNotFoundException())

        optionGroup.update(
            name = command.name!!,
            isRequired = command.isRequired,
            choices = command.choices.map { Option(
                name = it.name!!,
                price = it.price!!,
                isDefault = it.isDefault,
                state = it.state,
                displayOrder = it.displayOrder!!
            )},
            minChoices = command.minChoices,
            maxChoices = command.maxChoices,
            displayOrder = command.displayOrder!!
        )

        return OptionGroupDto.from(optionGroup)
    }

    @Transactional
    fun bulkUpdateOptionGroups(commands: List<OptionGroupDto>): List<OptionGroupDto> {
        return commands.map {
            updateOptionGroup(it.id!!, it)
        }
    }

    @Transactional
    fun deleteOptionGroup(optionGroupId: Long) {
        val optionGroup = (optionGroupRepository.findByIdOrNull(optionGroupId)
            ?: throw OptionGroupEntityNotFoundException())
        optionGroupRepository.delete(optionGroup)
    }
}