package com.dohyundev.pos.core.catalog.product

import com.dohyundev.common.entity.Activatable
import com.dohyundev.common.entity.TsidBaseEntity
import com.dohyundev.common.enum.TaxType
import com.dohyundev.pos.core.catalog.category.Category
import com.dohyundev.pos.core.catalog.option_group.OptionGroup
import jakarta.persistence.*

@Entity
class Product(
    @Column(nullable = false)
    var title: String,

    @Column(length = 500)
    var description: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    var category: Category,

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    var prices: MutableList<ProductPrice> = mutableListOf(),

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    var optionGroups: MutableList<ProductOptionGroup> = mutableListOf(),

    override var isActive: Boolean = true
) : TsidBaseEntity(), Activatable {

    fun update(
        category: Category = this.category,
        title: String = this.title,
        description: String? = this.description,
        prices: MutableList<ProductPrice>  = this.prices,
        optionGroups: Collection<OptionGroup> = this.optionGroups.map { it -> it.optionGroup },
        isActive: Boolean = this.isActive
    ) {
        this.category = category
        this.title = title
        this.description = description
        this.prices = prices
    }

    fun changeOptionGroups(optionGroups: Collection<ProductOptionGroup>) {
        this.optionGroups.clear()
        this.optionGroups.addAll(optionGroups)
    }
}
