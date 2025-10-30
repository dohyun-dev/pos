package com.dohyundev.pos.core.catalog.product.command.application

import com.dohyundev.pos.core.catalog.product.command.domain.ProductPriceType
import com.dohyundev.pos.core.catalog.product.command.domain.ProductState
import com.dohyundev.pos.core.catalog.product.command.domain.TaxType
import jakarta.validation.Valid
import jakarta.validation.constraints.*

interface ProductCommand {
    open class UpsertProduct(
        @field:NotBlank(message = "상품명은 필수입니다")
        @field:Size(max = 100, message = "상품명은 100자 이하여야 합니다")
        val name: String,
        
        @field:Size(max = 500, message = "상품 설명은 500자 이하여야 합니다")
        val description: String?,
        
        @field:NotNull(message = "카테고리는 필수입니다")
        @field:Positive(message = "카테고리 ID는 양수여야 합니다")
        val categoryId: Long?,

        @Valid
        val optionGroupIds: List<@NotNull(message = "옵션 그룹 ID는 필수입니다.") Long> = emptyList(),
        
        @field:Size(max = 100, message = "SKU는 100자 이하여야 합니다")
        val sku: String?,
        
        @field:Size(max = 100, message = "바코드는 100자 이하여야 합니다")
        val barcode: String?,
        
        @field:NotNull(message = "가격 정보는 필수입니다")
        @field:Valid
        val price: UpsertProductPrice,
        
        @field:NotNull(message = "상품 상태는 필수입니다")
        val state: ProductState,
        
        @field:NotNull(message = "활성화 여부는 필수입니다")
        val active: Boolean
    )

    data class UpsertProductPrice(
        @field:NotNull(message = "가격 타입은 필수입니다")
        val type: ProductPriceType,
        
        @field:NotNull(message = "가격은 필수입니다")
        @field:PositiveOrZero(message = "가격은 0 이상이어야 합니다")
        val price: Long,
        
        @field:NotNull(message = "단위는 필수입니다")
        @field:Positive(message = "단위는 양수여야 합니다")
        val unit: Int,
        
        @field:NotNull(message = "세금 타입은 필수입니다")
        val taxType: TaxType,
        
        @field:NotNull(message = "기본 가격 여부는 필수입니다")
        val isDefault: Boolean
    )

    class BulkUpdateProduct(
        @field:NotNull(message = "상품 ID는 필수입니다.")
        val id: Long,
        name: String,
        description: String?,
        categoryId: Long?,
        optionGroupIds: List<Long> = emptyList(),
        sku: String?,
        barcode: String?,
        price: UpsertProductPrice,
        state: ProductState,
        active: Boolean
    ): UpsertProduct(
        name = name,
        description = description,
        categoryId = categoryId,
        optionGroupIds = optionGroupIds,
        sku = sku,
        barcode = barcode,
        price = price,
        state = state,
        active = active
    )
}
