package com.dohyundev.pos.core.catalog.product

import com.dohyundev.common.enum.TaxType
import java.math.BigDecimal
import java.time.LocalDateTime

interface ProductResponse {
    data class Detail(
        val id: Long,
        val categoryId: Long,
        val categoryName: String,
        val title: String,
        val description: String?,
        val barcode: String?,
        val basePrice: BigDecimal,
        val taxType: TaxType,
        val optionGroups: List<OptionGroupSummary>,
        val createdAt: LocalDateTime?,
        val modifiedAt: LocalDateTime?
    ) {
        companion object {
            fun from(product: Product): Detail {
                return Detail(
                    id = product.id!!,
                    categoryId = product.category.id!!,
                    categoryName = product.category.title,
                    title = product.title,
                    description = product.description,
                    barcode = product.barcode,
                    basePrice = product.basePrice,
                    taxType = product.taxType,
                    optionGroups = product.optionGroups.map { 
                        OptionGroupSummary(
                            id = it.optionGroup.id!!,
                            title = it.optionGroup.title
                        )
                    },
                    createdAt = product.createdAt,
                    modifiedAt = product.modifiedAt
                )
            }
        }
    }

    data class Summary(
        val id: Long,
        val categoryId: Long,
        val categoryName: String,
        val title: String,
        val description: String?,
        val barcode: String?,
        val basePrice: BigDecimal,
        val taxType: TaxType
    ) {
        companion object {
            fun from(product: Product): Summary {
                return Summary(
                    id = product.id!!,
                    categoryId = product.category.id!!,
                    categoryName = product.category.title,
                    title = product.title,
                    description = product.description,
                    barcode = product.barcode,
                    basePrice = product.basePrice,
                    taxType = product.taxType
                )
            }
        }
    }
    
    data class OptionGroupSummary(
        val id: Long,
        val title: String
    )
}
