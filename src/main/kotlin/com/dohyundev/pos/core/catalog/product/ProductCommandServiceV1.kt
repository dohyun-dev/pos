package com.dohyundev.pos.core.catalog.product

import com.dohyundev.pos.core.catalog.category.CategoryRepository
import com.dohyundev.pos.core.catalog.option_group.OptionGroup
import com.dohyundev.pos.core.catalog.option_group.OptionGroupRepository
import jakarta.persistence.EntityNotFoundException
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
    fun createProduct(command: ProductCommand.CreateProduct): String {
        val category = categoryRepository.findByIdOrNull(command.categoryId!!)
            ?: throw EntityNotFoundException("카테고리를 찾을 수 없습니다. ID: ${command.categoryId}")
        
        val product = command.toProduct(category)

        val optionGroups = findOptionGroups(command.optionGroupIds)

        product.update(optionGroups = optionGroups)
        
        return productRepository.save(product).id!!
    }

    @Transactional
    fun updateProduct(command: ProductCommand.UpdateProduct) {
        val product = productRepository.findByIdOrNull(command.productId!!)
            ?: throw EntityNotFoundException("제품을 찾을 수 없습니다. ID: ${command.productId}")
        
        val category = categoryRepository.findByIdOrNull(command.categoryId!!)
            ?: throw EntityNotFoundException("카테고리를 찾을 수 없습니다. ID: ${command.categoryId}")

        val optionGroups = findOptionGroups(command.optionGroupIds)
        
        product.update(
            category = category,
            name = command.name!!,
            description = command.description,
            barcode = command.barcode,
            basePrice = command.basePrice!!,
            taxType = command.taxType!!,
            optionGroups = optionGroups,
        )
    }

    @Transactional
    fun deleteProduct(productId: String) {
        val product = productRepository.findByIdOrNull(productId)
            ?: throw EntityNotFoundException("제품을 찾을 수 없습니다. ID: $productId")
        
        productRepository.delete(product)
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
