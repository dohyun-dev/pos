package com.dohyundev.pos.core.catalog.product

import com.dohyundev.common.enum.TaxType
import com.dohyundev.common.vo.MoveDirection
import com.dohyundev.pos.core.catalog.category.Category
import jakarta.validation.Valid
import jakarta.validation.constraints.*
import java.math.BigDecimal

interface ProductCommand {
    data class Upsert(
        val id: Long? = null,

        @field:NotBlank(message = "상품명은 필수입니다.")
        @field:Size(max = 100, message = "상품명은 100자를 초과할 수 없습니다.")
        val title: String? = null,

        @field:Size(max = 500, message = "상품 설명은 500자를 초과할 수 없습니다.")
        val description: String? = null,

        @field:NotNull(message = "카테고리 ID는 필수입니다.")
        val categoryId: Long? = null,

        @field:NotEmpty(message = "가격 정보는 최소 1개 이상 필요합니다.")
        @field:Valid
        val prices: List<Price> = emptyList(),

        @field:Valid
        val optionGroupIds: List<Long> = emptyList(),

        @field:NotNull(message = "활성 여부는 필수입니다.")
        val isActive: Boolean? = true
        ) {
        fun toEntity(category: Category): Product {
            val product = Product(
                title = title!!,
                description = description,
                category = category,
                isActive = isActive!!
            )

            val priceEntities = prices.mapIndexed { i, p -> p.toEntity(product, i) }
            product.prices.addAll(priceEntities)

            return product
        }

        fun applyTo(product: Product) {
            product.title = title!!
            product.description = description
            product.isActive = isActive!!

            product.prices.clear()
            val newPrices = prices.mapIndexed { i, p -> p.toEntity(product, i) }
            product.prices.addAll(newPrices)
        }
    }

    data class UpdateProduct(
        @field:NotBlank(message = "제품 ID는 필수입니다.")
        val productId: Long?,
        
        @field:NotBlank(message = "카테고리 ID는 필수입니다.")
        val categoryId: Long?,
        
        @field:NotBlank(message = "제품명은 필수입니다.")
        @field:Size(max = 100, message = "제품명은 100자를 초과할 수 없습니다.")
        val title: String?,
        
        @field:Size(max = 500, message = "설명은 500자를 초과할 수 없습니다.")
        val description: String? = null,
        
        @field:Size(max = 50, message = "바코드는 50자를 초과할 수 없습니다.")
        val barcode: String? = null,
        
        @field:NotNull(message = "기본 가격은 필수입니다.")
        @field:DecimalMin(value = "0", message = "기본 가격은 0 이상이어야 합니다.")
        val basePrice: BigDecimal?,
        
        @field:NotNull(message = "세금 유형은 필수입니다.")
        val taxType: TaxType? = null,
        
        val optionGroupIds: List<String>? = null
    )

    data class ExchangeProductPosition(
        @field:NotBlank(message = "제품 ID는 필수입니다.")
        val productId: Long?,
        
        @field:NotNull(message = "이동 방향은 필수입니다.")
        val direction: MoveDirection?
    )

    data class Price(
        val id: Long? = null,

        @field:NotNull(message = "가격 유형은 필수입니다.")
        val productPriceType: ProductPriceType? = ProductPriceType.FIXED,

        @field:DecimalMin(value = "0.0", inclusive = true, message = "가격은 0 이상이어야 합니다.")
        val priceValue: BigDecimal? = BigDecimal.ZERO,

        @field:Positive(message = "단위는 1 이상이어야 합니다.")
        val priceUnit: Int? = 1,

        @field:NotNull(message = "세금 유형은 필수입니다.")
        val taxType: TaxType? = TaxType.TAX,

        @field:Size(max = 50, message = "바코드는 최대 50자까지 입력 가능합니다.")
        val barcode: String? = null
    ) {
        fun toEntity(product: Product, displayOrder: Long): ProductPrice {
            return ProductPrice(
                product = product,
                productPriceType = productPriceType!!,
                priceValue = priceValue!!,
                priceUnit = priceUnit!!,
                taxType = taxType!!,
                barcode = barcode,
                displayOrder = displayOrder
            )
        }
    }
}
