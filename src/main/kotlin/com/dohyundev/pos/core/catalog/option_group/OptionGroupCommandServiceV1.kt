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
        val optionGroup = OptionGroup(
            name = command.name,
            description = command.description,
            isRequired = command.isRequired,
            selectableOptionCount = command.selectableOptionCount
        )
        
        val options = command.options.mapIndexed { index, optionCommand ->
            Option(
                group = optionGroup,
                name = optionCommand.name,
                description = optionCommand.description,
                extraPrice = optionCommand.extraPrice,
                displayOrder = index.toLong()
            )
        }
        
        optionGroup.options.addAll(options)
        
        return optionGroupRepository.save(optionGroup).id!!
    }

    @Transactional
    fun updateOptionGroup(command: OptionGroupCommand.UpdateOptionGroup) {
        val optionGroup = optionGroupRepository.findByIdOrNull(command.optionGroupId)
            ?: throw EntityNotFoundException("옵션 그룹을 찾을 수 없습니다. ID: ${command.optionGroupId}")
        
        val updatedOptions = command.options.mapIndexed { index, optionCommand ->
            if (optionCommand.optionId != null) {
                // 기존 옵션 업데이트
                val existingOption = optionGroup.options.find { it.id == optionCommand.optionId }
                    ?: throw EntityNotFoundException("옵션을 찾을 수 없습니다. ID: ${optionCommand.optionId}")
                
                existingOption.update(
                    name = optionCommand.name,
                    description = optionCommand.description,
                    extraPrice = optionCommand.extraPrice
                )
                existingOption.changeDisplayOrder(index.toLong())
                existingOption
            } else {
                // 새로운 옵션 생성
                Option(
                    group = optionGroup,
                    name = optionCommand.name,
                    description = optionCommand.description,
                    extraPrice = optionCommand.extraPrice,
                    displayOrder = index.toLong()
                )
            }
        }.toMutableList()
        
        optionGroup.update(
            name = command.name,
            description = command.description,
            isRequired = command.isRequired,
            selectableOptionCount = command.selectableOptionCount,
            options = updatedOptions
        )
        
        command.isActive?.let { optionGroup.isActive = it }
    }

    @Transactional
    fun bulkUpdateOptionGroup(command: OptionGroupCommand.BulkUpdateOptionGroup) {
        command.commands.forEach { item ->
            val optionGroup = optionGroupRepository.findByIdOrNull(item.optionGroupId)
                ?: throw EntityNotFoundException("옵션 그룹을 찾을 수 없습니다. ID: ${item.optionGroupId}")
            
            optionGroup.name = item.name
            optionGroup.description = item.description
            optionGroup.changeDisplayOrder(item.displayOrder)
            
            item.isActive?.let { optionGroup.isActive = it }
        }
    }

    @Transactional
    fun deleteOptionGroup(optionGroupId: String) {
        val optionGroup = optionGroupRepository.findByIdOrNull(optionGroupId)
            ?: throw EntityNotFoundException("옵션 그룹을 찾을 수 없습니다. ID: $optionGroupId")
        
        optionGroupRepository.delete(optionGroup)
    }
}
