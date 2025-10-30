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

    @OneToMany(mappedBy="product", cascade = [CascadeType.ALL], orphanRemoval = true)
    var optionGroups: MutableList<ProductOptionGroup> = mutableListOf(),

    @Column(length = 100)
    var sku: String? = null,

    @Column(length = 100)
    var barcode: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var state: ProductState = ProductState.ON_SALE,

    @Column(nullable = false)
    override var active: Boolean = true,
): BaseEntity(), Activatable {
    @OneToOne(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    var price: ProductPrice? = null
        private set

    fun update(
        name: String = this.name,
        description: String? = this.description,
        category: Category = this.category,
        price: ProductPrice? = null,
        optionGroups: List<ProductOptionGroup>? = null,
        sku: String? = this.sku,
        barcode: String? = this.barcode,
        state: ProductState = this.state,
        active: Boolean = this.active,
    ) {
        this.name = name
        this.description = description
        this.category = category
        price?.let { this.price = it }
        optionGroups?.let { changeOptionGroups(it) }
        this.sku = sku
        this.barcode = barcode
        this.state = state
        this.active = active
    }

    fun changeOptionGroups(optionGroups: List<ProductOptionGroup>) {
        this.optionGroups.clear()
        this.optionGroups.addAll(optionGroups)
    }
}
