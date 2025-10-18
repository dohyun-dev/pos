package com.dohyundev.pos.core.catalog.product

import com.dohyundev.common.entity.TsidBaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class ProductPosition(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product,

    @Column(nullable = false)
    var position: Long
) : TsidBaseEntity() {
}