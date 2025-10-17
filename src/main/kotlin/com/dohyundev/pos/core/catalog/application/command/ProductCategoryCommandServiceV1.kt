package com.dohyundev.pos.core.catalog.application.command

import com.dohyundev.common.vo.MoveDirection
import com.dohyundev.pos.core.catalog.domain.entity.ProductCategory
import com.dohyundev.pos.core.catalog.domain.repository.ProductCategoryRepository
import com.dohyundev.pos.core.catalog.dto.ProductCategoryCommand
import jakarta.persistence.EntityNotFoundException
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductCategoryCommandServiceV1(
    private val productCategoryRepository: ProductCategoryRepository
) {
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun createProductCategory(command: ProductCategoryCommand.CreateProductCategory): ProductCategory {
        val category = command.toEntity()
        category.changeDisplayOrder(productCategoryRepository.findMaxDisplayOrder())
        return productCategoryRepository.save(category)
    }

    @Transactional
    fun updateProductCategory(categoryId: String, command: ProductCategoryCommand.UpdateProductCategory): ProductCategory {
        val productCategory = productCategoryRepository.findByIdOrNull(categoryId) ?: throw EntityNotFoundException()

        productCategory.update(
            name = command.name!!,
            description = productCategory.description,
        )

        return productCategory
    }

    @Transactional
    fun moveProductCategory(command: ProductCategoryCommand.MoveProductCategory): ProductCategory {
        val productCategory = productCategoryRepository.findByIdOrNull(command.categoryId!!)
            ?: throw EntityNotFoundException("ProductCategory not found: ${command.categoryId}")

        val targetCategory = when (command.direction!!) {
            MoveDirection.UP -> productCategoryRepository.findPrevious(productCategory.displayOrder)
            MoveDirection.DOWN -> productCategoryRepository.findNext(productCategory.displayOrder)
        }

        targetCategory?.let { productCategory.swapDisplayOrder(it) }

        return productCategory
    }

    @Transactional
    fun deleteProductCategory(categoryId: String) {
        val productCategory = productCategoryRepository.findByIdOrNull(categoryId) ?: throw EntityNotFoundException()
        productCategoryRepository.delete(productCategory)
    }

    @Transactional
    fun toggleActive(categoryId: String) {
        val productCategory = productCategoryRepository.findByIdOrNull(categoryId) ?: throw EntityNotFoundException()
        productCategory.toggleActive()
    }
}
