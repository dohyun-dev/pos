package com.dohyundev.pos.core.catalog.domain.entity

import com.dohyundev.common.entity.DisplayOrderable
import com.dohyundev.common.entity.SoftDeleteTsidBaseEntity
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
class ProductOption(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "m_option_group_id")
    var group: ProductOptionGroup,

    @Column(nullable = false)
    var name: String,

    var description: String? = null,

    @Column(precision = 15, scale = 2)
    var extraPrice: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    override var displayOrder: Long = 0,
    ) : SoftDeleteTsidBaseEntity<ProductOption>(), DisplayOrderable {
    fun update(
        name: String,
        extraPrice: BigDecimal?,
        description: String? = null,
    ) {
        this.name = name
        this.extraPrice = extraPrice ?: this.extraPrice
        this.description = description
    }
}
