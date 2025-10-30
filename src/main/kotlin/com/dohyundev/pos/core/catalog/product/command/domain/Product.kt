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

    @OneToOne(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    var price: ProductPrice,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var state: ProductState = ProductState.ON_SALE,

    @Column(nullable = false)
    override var active: Boolean = true
): BaseEntity(), Activatable {

}