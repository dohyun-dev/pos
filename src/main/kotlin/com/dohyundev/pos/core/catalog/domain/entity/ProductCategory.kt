package com.dohyundev.pos.core.catalog.domain.entity

import com.dohyundev.common.entity.TsidBaseEntity
import jakarta.persistence.*

@Entity
class ProductCategory(
    @Column(nullable = false)
    var name: String,

    var description: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: ProductCategory? = null,

    @Column(nullable = false)
    var isSummary: Boolean = false
) : TsidBaseEntity<ProductCategory>()
