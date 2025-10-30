package com.dohyundev.pos.core.catalog.product.dto

import com.dohyundev.pos.core.catalog.category.dto.CategoryDto
import com.dohyundev.pos.core.catalog.option_group.command.dto.OptionGroupDto
import com.dohyundev.pos.core.catalog.product.command.domain.Product
import com.dohyundev.pos.core.catalog.product.command.domain.ProductState
import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.dohyundev.pos.core.catalog.product.command.domain.Product}
 */
data class ProductDto(
    val id: Long? = null,
    val name: String? = null,
    val description: String? = null,
    val category: CategoryDto? = null,
    val optionGroups: List<OptionGroupDto> = mutableListOf(),
    val sku: String? = null,
    val barcode: String? = null,
    val price: ProductPriceDto? = null,
    val state: ProductState = ProductState.ON_SALE,
    val active: Boolean = true,
    val createdAt: LocalDateTime? = null,
    val modifiedAt: LocalDateTime? = null,
    val createdBy: Long? = null,
    val modifiedBy: Long? = null,
) : Serializable {
    companion object {
        fun from(product: Product): ProductDto {
            return ProductDto(
                id = product.id,
                name = product.name,
                description = product.description,
                category = CategoryDto.from(product.category),
                optionGroups = product.optionGroups.map { OptionGroupDto.from(it.optionGroup) },
                sku = product.sku,
                barcode = product.barcode,
                price = ProductPriceDto.from(product.price),
                state = product.state,
                active = product.active,
                createdAt = product.createdAt,
                modifiedAt = product.modifiedAt,
                createdBy = product.createdBy,
                modifiedBy = product.modifiedBy
            )
        }
    }
}