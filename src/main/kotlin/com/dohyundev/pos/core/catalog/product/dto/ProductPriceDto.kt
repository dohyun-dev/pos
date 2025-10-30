package com.dohyundev.pos.core.catalog.product.dto

import com.dohyundev.pos.core.catalog.product.command.domain.ProductPrice
import com.dohyundev.pos.core.catalog.product.command.domain.ProductPriceType
import com.dohyundev.pos.core.catalog.product.command.domain.TaxType
import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.dohyundev.pos.core.catalog.product.command.domain.ProductPrice}
 */
data class ProductPriceDto(
    val id: Long? = null,
    val productId: Long? = null,
    val type: ProductPriceType = ProductPriceType.FIXED,
    val price: Long = 0,
    val unit: Int = 1,
    val taxType: TaxType = TaxType.TAXABLE,
    val isDefault: Boolean = false,
    val createdAt: LocalDateTime? = null,
    val modifiedAt: LocalDateTime? = null,
    val createdBy: Long? = null,
    val modifiedBy: Long? = null,
) : Serializable {
    companion object {
        fun from(productPrice: ProductPrice): ProductPriceDto {
            ProductPriceDto(
                id = productPrice.id,
                productId = productPrice.product.id,
                type = productPrice.type,
                price = productPrice.price,
                unit = productPrice.unit,
                taxType = productPrice.taxType,
                isDefault = productPrice.isDefault,
                createdAt = productPrice.createdAt,
                modifiedAt = productPrice.modifiedAt,
                createdBy = productPrice.createdBy,
                modifiedBy = productPrice.modifiedBy
            )
        }
    }
}