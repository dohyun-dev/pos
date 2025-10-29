package com.dohyundev.pos.core.catalog.product.command.domain

import com.dohyundev.common.entity.BaseEntity
import com.dohyundev.pos.core.catalog.option_group.command.domain.OptionGroup
import jakarta.persistence.*

@Entity
@Table(name = "product_option_groups")
class ProductOptionGroup(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    val product: Product,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_group_id", nullable = false)
    val optionGroup: OptionGroup,
): BaseEntity() {
}