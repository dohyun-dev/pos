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
    fun createOptionGroup(command: OptionGroupCommand.CreateProductOptionGroup) {
        // TODO
    }

    @Transactional
    fun updateOptionGroup(command: OptionGroupCommand.UpdateOptionGroup) {
        //TODO
    }

    @Transactional
    fun bulkUpdateOptionGroup(command: OptionGroupCommand.BulkUpdateOptionGroup) {
        // TODO
    }

    @Transactional
    fun deleteOptionGroup(optionGroupId: String) {
        val optionGroup = optionGroupRepository.findByIdOrNull(optionGroupId) ?: throw EntityNotFoundException()
        optionGroupRepository.delete(optionGroup)
    }
}
