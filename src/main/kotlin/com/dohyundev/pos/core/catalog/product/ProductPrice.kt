package com.dohyundev.pos.core.catalog.product

import com.dohyundev.common.entity.DisplayOrderable
import com.dohyundev.common.entity.TsidBaseEntity
import com.dohyundev.common.enum.TaxType
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "product_prices")
data class ProductPrice(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    val productPriceType: ProductPriceType = ProductPriceType.FIXED,

    @Column(nullable = false, precision = 15, scale = 2)
    val priceValue: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    val priceUnit: Int = 1,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var taxType: TaxType = TaxType.TAX,

    @Column(length = 50)
    val barcode: String? = null,

    override var displayOrder: Long
): TsidBaseEntity(), DisplayOrderable {
}
