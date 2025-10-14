package com.dohyundev.pos.core.catalog.application.command

import com.dohyundev.pos.core.catalog.domain.entity.ProductOption
import com.dohyundev.pos.core.catalog.domain.repository.ProductOptionGroupRepository
import com.dohyundev.pos.core.catalog.domain.repository.ProductOptionRepository
import com.dohyundev.pos.core.catalog.dto.ProductCommand
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class ProductOptionCommandServiceV1(
    private val optionGroupRepository: ProductOptionGroupRepository,
    private val optionRepository: ProductOptionRepository
) {
    @Transactional
    fun create(groupId: String, command: ProductCommand.CreateOption): ProductOption {
        val group = optionGroupRepository.findById(groupId)
            .orElseThrow { EntityNotFoundException("옵션 그룹이 존재하지 않습니다. $groupId") }

        val option = command.toEntity(group)
        return optionRepository.save(option)
    }

    @Transactional
    fun update(optionId: String, command: ProductCommand.UpdateOption): ProductOption {
        val option = optionRepository.findById(optionId)
            .orElseThrow { IllegalArgumentException("옵션을 찾을 수 없습니다: $optionId") }

        option.update(
            name = command.name!!,
            description = command.description,
            displayOrder = command.displayOrder,
            extraPrice = command.extraPrice,
            defaultSelected = command.defaultSelected
        )

        return optionRepository.save(option)
    }

    @Transactional
    fun delete(optionId: String) {
        val option = optionRepository.findById(optionId)
            .orElseThrow { IllegalArgumentException("옵션을 찾을 수 없습니다: $optionId") }
        optionRepository.delete(option)
    }
}
