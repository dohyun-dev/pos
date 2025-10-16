package com.dohyundev.pos.core.catalog.application

import com.dohyundev.pos.core.catalog.domain.entity.ProductOptionGroup
import com.dohyundev.pos.core.catalog.domain.repository.ProductOptionGroupRepository
import com.dohyundev.pos.core.catalog.dto.ProductOptionCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductOptionCommandServiceV1(
    private val productOptionGroupRepository: ProductOptionGroupRepository
) {
    @Transactional
    fun createProductOptionGroup(command: ProductOptionCommand.CreateProductOptionGroup): ProductOptionGroup {
        val productOptionGroup = command.toEntity()
        return productOptionGroupRepository.save(productOptionGroup)
    }

    @Transactional
    fun updateProductOptionGroup(groupId: String, command: ProductOptionCommand.CreateProductOptionGroup): ProductOptionGroup {
        val group = productOptionGroupRepository.findById(groupId)
            .orElseThrow { IllegalArgumentException("옵션 그룹을 찾을 수 없습니다: $groupId") }

        group.update(
            name = command.name!!,
            description = command.description,
            isRequired = command.isRequired ?: false,
            selectableOptionCount = command.selectableOptionCount ?: 0,
        )

        val options = command.options.map { it.toEntity(group) }

        group.changeOptions(options)

        return productOptionGroupRepository.save(group)
    }

    @Transactional
    fun deleteProductOptionGroup(groupId: String) {
        val group = productOptionGroupRepository.findById(groupId)
            .orElseThrow { IllegalArgumentException("옵션 그룹을 찾을 수 없습니다: $groupId") }
        productOptionGroupRepository.delete(group)
    }
}
