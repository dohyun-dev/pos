package com.dohyundev.pos.core.catalog.product.command.domain

import com.dohyundev.common.entity.BaseEntity
import com.dohyundev.pos.core.catalog.option_group.command.domain.Option
import jakarta.persistence.*

@Entity
@Table(name = "product_variant_options")
class ProductVariantOption(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_variant_id", nullable = false)
    var productVariant: ProductVariant,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id", nullable = false)
    var option: Option,

    @Column(nullable = false)
    var displayOrder: Int = 0
) : BaseEntity()