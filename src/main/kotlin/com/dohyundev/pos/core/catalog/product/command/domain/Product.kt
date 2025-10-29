package com.dohyundev.pos.core.catalog.product.command.domain

import com.dohyundev.common.entity.Activatable
import com.dohyundev.common.entity.BaseEntity
import com.dohyundev.pos.core.catalog.category.command.domain.Category
import jakarta.persistence.*

@Entity
class Product(
    @Column(nullable = false)
    var name: String,

    var description: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    var category: Category,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    var prices: MutableList<ProductPrice> = mutableListOf(),

    @OneToMany(mappedBy="product", cascade = [CascadeType.ALL], orphanRemoval = true)
    var optionGroups: MutableList<ProductOptionGroup> = mutableListOf(),

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var state: ProductState = ProductState.ON_SALE,

    override var active: Boolean = true
): BaseEntity(), Activatable {

}