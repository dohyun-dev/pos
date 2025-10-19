package com.dohyundev.pos.core.catalog.product

import com.dohyundev.common.entity.TsidBaseEntity
import com.dohyundev.pos.core.catalog.option_group.OptionGroup
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class ProductOptionGroup(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_group_id")
    val optionGroup: OptionGroup,
): TsidBaseEntity()