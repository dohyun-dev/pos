package com.dohyundev.pos.core.catalog.option_group

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OptionGroupCommandServiceV1(
    private val optionGroupRepository: OptionGroupRepository
) {
    @Transactional
    fun createOptionGroup(command: OptionGroupCommand.CreateProductOptionGroup): String {
        val optionGroup = command.toOptionGroup()

        val options = command.options!!.mapIndexed { index, optionCommand ->
            optionCommand.toOption(optionGroup, index.toLong())
        }

        optionGroup.changeOptions(options)
        
        return optionGroupRepository.save(optionGroup).id!!
    }

    @Transactional
    fun updateOptionGroup(command: OptionGroupCommand.UpdateOptionGroup) {
        val optionGroup = optionGroupRepository.findByIdOrNull(command.optionGroupId!!)
            ?: throw EntityNotFoundException("옵션 그룹을 찾을 수 없습니다. ID: ${command.optionGroupId}")
        
        val updatedOptions = command.toOptions(optionGroup)
        
        optionGroup.update(
            name = command.name!!,
            description = command.description,
            isRequired = command.isRequired!!,
            selectableOptionCount = command.selectableOptionCount ?: 1,
            options = updatedOptions,
            isActive = command.isActive!!
        )
    }

    @Transactional
    fun bulkUpdateOptionGroup(command: OptionGroupCommand.BulkUpdateOptionGroup) {
        command.commands!!.forEach { item ->
            val optionGroup = optionGroupRepository.findByIdOrNull(item.optionGroupId!!)
                ?: throw EntityNotFoundException("옵션 그룹을 찾을 수 없습니다. ID: ${item.optionGroupId}")

            optionGroup.update(
                name = item.name!!,
                description = item.description,
                displayOrder = item.displayOrder!!,
                isActive = item.isActive!!
            )
        }
    }

    @Transactional
    fun deleteOptionGroup(optionGroupId: String) {
        val optionGroup = optionGroupRepository.findByIdOrNull(optionGroupId)
            ?: throw EntityNotFoundException("옵션 그룹을 찾을 수 없습니다. ID: $optionGroupId")
        
        optionGroupRepository.delete(optionGroup)
    }
}
