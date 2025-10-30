package com.dohyundev.pos.core.catalog.product.command.application

import com.dohyundev.pos.core.catalog.category.command.domain.CategoryRepository
import com.dohyundev.pos.core.catalog.category.command.exception.CategoryEntityNotFoundException
import com.dohyundev.pos.core.catalog.option_group.command.domain.OptionGroupRepository
import com.dohyundev.pos.core.catalog.option_group.command.exception.OptionGroupEntityNotFoundException
import com.dohyundev.pos.core.catalog.product.command.domain.Product
import com.dohyundev.pos.core.catalog.product.command.domain.ProductOptionGroup
import com.dohyundev.pos.core.catalog.product.command.domain.ProductPrice
import com.dohyundev.pos.core.catalog.product.command.domain.ProductRepository
import com.dohyundev.pos.core.catalog.product.command.exception.ProductEntityNotFoundException
import com.dohyundev.pos.core.catalog.product.dto.ProductDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductCommandServiceV1(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val optionGroupRepository: OptionGroupRepository,
) {
    @Transactional
    fun createProduct(command: ProductCommand.UpsertProduct): ProductDto {
        // 1. Category 조회
        val category = categoryRepository.findByIdOrNull(command.categoryId!!)
            ?: throw CategoryEntityNotFoundException()

        // 2. Product 생성
        val product = Product(
            name = command.name,
            description = command.description,
            category = category,
            sku = command.sku,
            barcode = command.barcode,
            state = command.state,
            active = command.active
        )

        // 3. ProductPrice 생성 및 연결
        val productPrice = ProductPrice(
            product = product,
            type = command.price.type,
            price = command.price.price,
            unit = command.price.unit,
            taxType = command.price.taxType,
            isDefault = command.price.isDefault
        )

        // 4. OptionGroup 조회 및 ProductOptionGroup 생성
        val productOptionGroups: List<ProductOptionGroup> = if (command.optionGroupIds.isNotEmpty()) {
            val optionGroups = optionGroupRepository.findAllById(command.optionGroupIds)
            if (optionGroups.size != command.optionGroupIds.size) {
                throw OptionGroupEntityNotFoundException()
            }
            optionGroups.map { ProductOptionGroup(product = product, optionGroup = it) }
        } else {
            emptyList()
        }

        // 5. Product 업데이트 (price와 optionGroups 설정)
        product.update(
            price = productPrice,
            optionGroups = productOptionGroups
        )

        // 6. 저장 (cascade로 인해 productPrice와 optionGroups도 함께 저장됨)
        val savedProduct = productRepository.save(product)

        // 7. DTO 변환 및 반환
        return ProductDto.from(savedProduct)
    }

    @Transactional
    fun updateProduct(productId: Long, command: ProductCommand.UpsertProduct): ProductDto {
        val product = productRepository.findByIdOrNull(productId)
            ?: throw ProductEntityNotFoundException()

        val category = categoryRepository.findByIdOrNull(command.categoryId!!)
            ?: throw CategoryEntityNotFoundException()

        val productPrice = ProductPrice(
            product = product,
            type = command.price.type,
            price = command.price.price,
            unit = command.price.unit,
            taxType = command.price.taxType,
            isDefault = command.price.isDefault
        )

        val productOptionGroups: List<ProductOptionGroup> = if (command.optionGroupIds.isNotEmpty()) {
            val optionGroups = optionGroupRepository.findAllById(command.optionGroupIds)
            if (optionGroups.size != command.optionGroupIds.size) {
                throw OptionGroupEntityNotFoundException()
            }
            optionGroups.map { ProductOptionGroup(product = product, optionGroup = it) }
        } else {
            emptyList()
        }

        product.update(
            name = command.name,
            description = command.description,
            category = category,
            sku = command.sku,
            barcode = command.barcode,
            state = command.state,
            active = command.active,
            price = productPrice,
            optionGroups = productOptionGroups
        )


        return ProductDto.from(product)
    }

    @Transactional
    fun bulkUpdateProduct(commands: List<ProductCommand.BulkUpdateProduct>): List<ProductDto> {
        return commands.map {
            updateProduct(it.id, it)
        }
    }

    @Transactional
    fun deleteProduct(productId: Long) {
        val product = productRepository.findByIdOrNull(productId)
            ?: throw ProductEntityNotFoundException()
        productRepository.delete(product)
    }
}
