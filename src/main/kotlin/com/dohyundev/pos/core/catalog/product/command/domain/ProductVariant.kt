package com.dohyundev.pos.core.catalog.product.command.domain

import com.dohyundev.common.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "product_variants")
class ProductVariant(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    var product: Product,

    @Column(nullable = false, length = 100)
    var sku: String,

    @Column(length = 100)
    var barcode: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var state: ProductState = ProductState.ON_SALE,

    @Column(nullable = false)
    var stockQuantity: Int = 0,

    @OneToMany(mappedBy = "variant", cascade = [CascadeType.ALL], orphanRemoval = true)
    var prices: MutableList<ProductPrice> = mutableListOf(),

    @OneToMany(mappedBy = "productVariant", cascade = [CascadeType.ALL], orphanRemoval = true)
    var options: MutableList<ProductVariantOption> = mutableListOf()
) : BaseEntity()
