package com.dohyundev.pos.core.catalog.option_group.command

import com.dohyundev.pos.core.catalog.option_group.command.domain.OptionGroupRepository
import com.dohyundev.pos.core.catalog.option_group.command.dto.OptionGroupDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OptionGroupQueryServiceV1(
    private val optionGroupRepository: OptionGroupRepository
) {
    fun getOptionGroups(): List<OptionGroupDto> {
        return optionGroupRepository.findAll()
            .map { OptionGroupDto.from(it) }
    }
}
