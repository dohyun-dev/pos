package com.dohyundev.pos.core.catalog.option_group.application

import com.dohyundev.pos.core.catalog.option_group.domain.OptionGroup
import com.dohyundev.pos.core.catalog.option_group.domain.OptionGroupRepository
import com.dohyundev.pos.core.catalog.option_group.dto.OptionGroupCommand
import com.dohyundev.pos.core.catalog.product.domain.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class OptionGroupCommandService(
    private val optionGroupRepository: OptionGroupRepository,
    private val productRepository: ProductRepository
) {
    fun create(command: OptionGroupCommand.Create): OptionGroup {
        val product = productRepository.findById(command.productId).orElseThrow { IllegalArgumentException("Product not found") }
        val optionGroup = OptionGroup(command.name, command.description, command.priority, command.required, product)
        return optionGroupRepository.save(optionGroup)
    }

    fun update(command: OptionGroupCommand.Update): OptionGroup {
        val optionGroup = optionGroupRepository.findById(command.optionGroupId)
            .orElseThrow { IllegalArgumentException("OptionGroup not found") }
        command.name?.let { optionGroup.name = it }
        command.description?.let { optionGroup.description = it }
        command.priority?.let { optionGroup.priority = it }
        command.required?.let { optionGroup.required = it }
        return optionGroup
    }

    fun delete(command: OptionGroupCommand.Delete) {
        optionGroupRepository.deleteById(command.optionGroupId)
    }
}
