package com.dohyundev.pos.core.catalog.option_group

import com.dohyundev.common.entity.DisplayOrderable
import com.dohyundev.common.entity.TsidBaseEntity
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "product_options")
class Option(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_group_id")
    var group: OptionGroup,

    @Column(nullable = false)
    var title: String,

    var description: String? = null,

    @Column(precision = 15, scale = 2)
    var priceValue: BigDecimal = BigDecimal.ZERO,

    var imageUrl: String? = null,

    @Column(nullable = false)
    override var displayOrder: Long = 0,
    ) : TsidBaseEntity(), DisplayOrderable {

    fun update(
        title: String = this.title,
        description: String? = this.description,
        extraPrice: BigDecimal = this.priceValue,
        displayOrder: Long = this.displayOrder,
    ) {
        this.title = title
        this.priceValue = extraPrice
        this.description = description
        this.displayOrder = displayOrder
    }
}
