package com.dohyundev.pos.core.catalog.option.application

import com.dohyundev.pos.core.catalog.option.domain.Option
import com.dohyundev.pos.core.catalog.option.domain.OptionRepository
import com.dohyundev.pos.core.catalog.option.dto.OptionCommand
import com.dohyundev.pos.core.catalog.option_group.domain.OptionGroupRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class OptionCommandService(
    private val optionRepository: OptionRepository,
    private val optionGroupRepository: OptionGroupRepository
) {
    fun create(command: OptionCommand.Create): Option {
        val optionGroup = optionGroupRepository.findById(command.optionGroupId).orElseThrow { IllegalArgumentException("OptionGroup not found") }
        val option = Option(command.name, command.description, command.priority, command.price, optionGroup)
        return optionRepository.save(option)
    }

    fun update(command: OptionCommand.Update): Option {
        val option = optionRepository.findById(command.optionId).orElseThrow { IllegalArgumentException("Option not found") }
        command.name?.let { option.name = it }
        command.description?.let { option.description = it }
        command.price?.let { option.price = it }
        command.priority?.let { option.priority = it }
        return option
    }

    fun delete(command: OptionCommand.Delete) {
        optionRepository.deleteById(command.optionId)
    }
}