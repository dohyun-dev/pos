package com.dohyundev.pos.core.catalog.option_group.domain

import com.dohyundev.pos.core.catalog.product.domain.Product
import com.dohyundev.pos.core.common.entity.TsidBaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class OptionGroup(
    var name: String,
    var description: String?,
    var priority: Int = 0,
    val required: Boolean = false,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product,
) : TsidBaseEntity<OptionGroup>() {
}