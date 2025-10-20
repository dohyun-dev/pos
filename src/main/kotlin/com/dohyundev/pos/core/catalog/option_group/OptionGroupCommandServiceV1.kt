package com.dohyundev.pos.core.catalog.option_group

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OptionGroupCommandServiceV1(
    private val optionGroupRepository: OptionGroupRepository,
    private val optionRepository: OptionRepository
) {
    @Transactional
    fun upsertOptionGroup(command: OptionGroupAggregateCommand.UpsertOptionGroupCommand): Long {
        val group = if (command.id == null) {
            command.toNewEntity()
        } else {
            optionGroupRepository.findByIdOrNull(command.id) ?: throw EntityNotFoundException("옵션 그룹을 찾을 수 없습니다. ID: ${command.id}")
        }

        command.applyTo(group, optionRepository)

        return optionGroupRepository.save(group).id!!
    }

    @Transactional
    fun bulkUpsertOptionGroup(command: OptionGroupAggregateCommand.BulkUpsertOptionGroupCommand) {
        command.commands.forEach { upsertOptionGroup(it) }
    }

    @Transactional
    fun deleteOptionGroup(optionGroupId: Long) {
        val group = optionGroupRepository.findByIdOrNull(optionGroupId)
            ?: throw EntityNotFoundException("옵션 그룹을 찾을 수 없습니다. ID: $optionGroupId")
        optionGroupRepository.delete(group)
    }
}
