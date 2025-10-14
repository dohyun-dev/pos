package com.dohyundev.pos.core.catalog.domain.entity

import com.dohyundev.common.entity.TsidBaseEntity
import jakarta.persistence.*

@Entity
class ProductOptionGroup(
    /** 연결된 상품 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product,

    @Column(nullable = false)
    var name: String,

    var description: String? = null,

    var isRequired: Boolean = false,

    var isMultiSelect: Boolean = false,

    @OneToMany(mappedBy = "group", cascade = [CascadeType.ALL])
    var productOptions: MutableList<ProductOption> = mutableListOf(),

    var displayOrder: Int = 0,
) : TsidBaseEntity<ProductOptionGroup>() {
    fun update(
        name: String,
        isRequired: Boolean?,
        isMultiSelect: Boolean?,
        description: String? = null,
        displayOrder: Int?,
    ) {
        this.name = name
        this.isRequired = isRequired ?: this.isRequired
        this.isMultiSelect = isMultiSelect ?: this.isMultiSelect
        this.description = description
        this.displayOrder = displayOrder ?: this.displayOrder
    }
}
