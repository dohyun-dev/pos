package com.dohyundev.pos.core.catalog.product

import com.dohyundev.pos.core.catalog.category.CategoryRepository
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
        val category = categoryRepository.findByIdOrNull(command.categoryId)
            ?: throw EntityNotFoundException("카테고리를 찾을 수 없습니다. ID: ${command.categoryId}")
        
        val product = Product(
            category = category,
            name = command.name,
            description = command.description,
            barcode = command.barcode,
            basePrice = command.basePrice,
            taxType = command.taxType
        )
        
        if (command.optionGroupIds.isNotEmpty()) {
            val optionGroups = optionGroupRepository.findAllById(command.optionGroupIds)
            if (optionGroups.size != command.optionGroupIds.size) {
                throw EntityNotFoundException("일부 옵션 그룹을 찾을 수 없습니다.")
            }
            product.updateOptionGroups(optionGroups)
        }
        
        return productRepository.save(product).id!!
    }

    @Transactional
    fun updateProduct(command: ProductCommand.UpdateProduct) {
        val product = productRepository.findByIdOrNull(command.productId)
            ?: throw EntityNotFoundException("제품을 찾을 수 없습니다. ID: ${command.productId}")
        
        val category = categoryRepository.findByIdOrNull(command.categoryId)
            ?: throw EntityNotFoundException("카테고리를 찾을 수 없습니다. ID: ${command.categoryId}")
        
        product.update(
            category = category,
            name = command.name,
            description = command.description,
            barcode = command.barcode,
            basePrice = command.basePrice,
            taxType = command.taxType
        )
        
        if (command.optionGroupIds.isNotEmpty()) {
            val optionGroups = optionGroupRepository.findAllById(command.optionGroupIds)
            if (optionGroups.size != command.optionGroupIds.size) {
                throw EntityNotFoundException("일부 옵션 그룹을 찾을 수 없습니다.")
            }
            product.updateOptionGroups(optionGroups)
        } else {
            product.optionGroups.clear()
        }
    }

    @Transactional
    fun deleteProduct(productId: String) {
        val product = productRepository.findByIdOrNull(productId)
            ?: throw EntityNotFoundException("제품을 찾을 수 없습니다. ID: $productId")
        
        productRepository.delete(product)
    }
}
