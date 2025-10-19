package com.dohyundev.pos.core.catalog.option_group

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OptionQueryServiceV1(
    private val optionGroupRepository: OptionGroupRepository
) {
    fun getOptionGroups(): List<OptionGroupResponse.Summary> {
        return optionGroupRepository.findAll()
            .sortedBy { it.displayOrder }
            .map { OptionGroupResponse.Summary.from(it) }
    }

    fun getOptionGroup(optionGroupId: String): OptionGroupResponse.Detail {
        val optionGroup = optionGroupRepository.findByIdOrNull(optionGroupId)
            ?: throw EntityNotFoundException("옵션 그룹을 찾을 수 없습니다. ID: $optionGroupId")
        
        return OptionGroupResponse.Detail.from(optionGroup)
    }
}
