package com.dohyundev.pos.core.catalog.product

import com.dohyundev.pos.core.catalog.category.CategoryRepository
import com.dohyundev.pos.core.catalog.category.RemoveCategoryEvent
import com.dohyundev.pos.core.catalog.option_group.OptionGroup
import com.dohyundev.pos.core.catalog.option_group.OptionGroupRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.context.event.EventListener
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductCommandServiceV1(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val optionGroupRepository: OptionGroupRepository
) {
    @Transactional
    fun createProduct(command: ProductCommand.Upsert): Long {
        val category = categoryRepository.findByIdOrNull(command.categoryId!!)
            ?: throw EntityNotFoundException("카테고리를 찾을 수 없습니다. ID=${command.categoryId}")

        val product = command.toEntity(category)

        val optionGroups = optionGroupRepository.findAllById(command.optionGroupIds)
        val productOptionGroups = optionGroups.mapIndexed { i, og ->
            ProductOptionGroup(product = product, optionGroup = og, displayOrder = i.toLong())
        }
        product.changeOptionGroups(productOptionGroups)

        return productRepository.save(product).id!!
    }

    @Transactional
    fun updateProduct(command: ProductCommand.Upsert) {
        val product = productRepository.findByIdOrNull(command.id!!)
            ?: throw EntityNotFoundException("상품을 찾을 수 없습니다. ID=${command.id}")

        command.applyTo(product)

        val optionGroups = optionGroupRepository.findAllById(command.optionGroupIds)
        val productOptionGroups = optionGroups.mapIndexed { i, og ->
            ProductOptionGroup(product = product, optionGroup = og, displayOrder = i.toLong())
        }
        product.changeOptionGroups(productOptionGroups)
    }

    @Transactional
    fun deleteProduct(productId: Long) {
        val product = productRepository.findByIdOrNull(productId)
            ?: throw EntityNotFoundException("제품을 찾을 수 없습니다. ID: $productId")
        
        productRepository.delete(product)
    }

    @Transactional
    @EventListener
    fun handleRemoveCategoryEvent(event: RemoveCategoryEvent) {
        if (productRepository.existsByCategory(event.category))
            throw IllegalStateException("카테고리 내 상품이 있어서 삭제할 수 없습니다.")
    }

    private fun findOptionGroups(optionGroupIds: List<String>?): List<OptionGroup> {
        val ids = optionGroupIds ?: return emptyList()
        if (ids.isEmpty()) return emptyList()

        val optionGroups = optionGroupRepository.findAllById(ids)
        if (optionGroups.size != ids.size) {
            throw EntityNotFoundException("일부 옵션 그룹을 찾을 수 없습니다.")
        }
        return optionGroups
    }
}
