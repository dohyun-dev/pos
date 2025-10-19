package com.dohyundev.pos.core.catalog.product

import com.dohyundev.common.entity.TsidBaseEntity
import com.dohyundev.common.enum.TaxType
import com.dohyundev.pos.core.catalog.category.Category
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
    var taxType: TaxType = TaxType.TAX,
) : TsidBaseEntity() {

}
