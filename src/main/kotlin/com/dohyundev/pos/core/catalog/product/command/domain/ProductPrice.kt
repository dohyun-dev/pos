package com.dohyundev.pos.core.catalog.product.command.domain

import com.dohyundev.common.entity.BaseEntity
import jakarta.persistence.*

@Entity
class ProductPrice(
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    val product: Product,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var type: ProductPriceType = ProductPriceType.FIXED,

    @Column(nullable = false)
    var price: Long = 0,

    @Column(nullable = false)
    var unit: Int = 1,

    @Enumerated(EnumType.STRING)
    var taxType: TaxType = TaxType.TAXABLE,

    @Column(nullable = false)
    var isDefault: Boolean = false,
): BaseEntity()