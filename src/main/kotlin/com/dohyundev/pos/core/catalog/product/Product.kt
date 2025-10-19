package com.dohyundev.pos.core.catalog.product

import com.dohyundev.common.entity.TsidBaseEntity
import com.dohyundev.common.enum.TaxType
import com.dohyundev.pos.core.catalog.category.Category
import com.dohyundev.pos.core.catalog.option_group.OptionGroup
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
class Product(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    var category: Category,

    /** 제품명 */
    @Column(nullable = false)
    var name: String,

    /** 설명 */
    @Column(length = 500)
    var description: String? = null,

    /** 바코드  (UPC, EAN 등)*/
    @Column(length = 50)
    var barcode: String? = null,

    @Column(precision = 15, scale = 2)
    var basePrice: BigDecimal = BigDecimal.ZERO,

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    var optionGroups: MutableList<ProductOption> = mutableListOf(),

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var taxType: TaxType = TaxType.TAX,
) : TsidBaseEntity() {

    fun update(
        category: Category,
        name: String,
        description: String?,
        barcode: String?,
        basePrice: BigDecimal,
        taxType: TaxType
    ) {
        this.category = category
        this.name = name
        this.description = description
        this.barcode = barcode
        this.basePrice = basePrice
        this.taxType = taxType
    }

    fun updateOptionGroups(optionGroups: List<OptionGroup>) {
        this.optionGroups.clear()
        this.optionGroups.addAll(
            optionGroups.map { ProductOption(product = this, optionGroup = it) }
        )
    }
}
