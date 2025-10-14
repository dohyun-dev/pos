package com.dohyundev.pos.core.catalog.application

import com.dohyundev.pos.core.catalog.domain.entity.ProductOptionGroup
import com.dohyundev.pos.core.catalog.domain.repository.ProductOptionGroupRepository
import com.dohyundev.pos.core.catalog.domain.repository.ProductRepository
import com.dohyundev.pos.core.catalog.dto.ProductCommand
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductOptionGroupCommandServiceV1(
    private val productRepository: ProductRepository,
    private val optionGroupRepository: ProductOptionGroupRepository
) {

    /** 옵션 그룹 생성 */
    @Transactional
    fun create(productId: String, command: ProductCommand.CreateOptionGroup): ProductOptionGroup {
        val product = productRepository.findById(productId)
            .orElseThrow { EntityNotFoundException("상품 정보가 존재하지 않습니다. $productId") }

        val group = command.toEntity(product)
        return optionGroupRepository.save(group)
    }

    /** 옵션 그룹 수정 */
    @Transactional
    fun update(groupId: String, command: ProductCommand.UpdateOptionGroup): ProductOptionGroup {
        val group = optionGroupRepository.findById(groupId)
            .orElseThrow { IllegalArgumentException("옵션 그룹을 찾을 수 없습니다: $groupId") }

        group.update(
            name = command.name!!,
            description = command.description,
            isRequired = command.isRequired ?: false,
            isMultiSelect = command.isMultiSelect ?: false,
            displayOrder = command.displayOrder
        )

        return optionGroupRepository.save(group)
    }

    /** 옵션 그룹 삭제 */
    @Transactional
    fun delete(groupId: String) {
        val group = optionGroupRepository.findById(groupId)
            .orElseThrow { IllegalArgumentException("옵션 그룹을 찾을 수 없습니다: $groupId") }
        optionGroupRepository.delete(group)
    }
}
