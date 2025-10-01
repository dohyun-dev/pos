package com.dohyundev.pos.core.catalog.product.domain

import com.dohyundev.pos.core.common.entity.TsidBaseEntity
import jakarta.persistence.Entity

@Entity
class Product(
    var name: String,
    var description: String?,
    var price: Long,
    var priority: Int = 0,
) : TsidBaseEntity<Product>() {
}