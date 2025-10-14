package com.dohyundev.pos.core.catalog.domain.entity

import com.dohyundev.common.entity.TsidBaseEntity
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
class Product(
    /** 고유 코드 */
    @Column(nullable = false, unique = true)
    var code: String,

    /** 바코드  (UPC, EAN 등)*/
    @Column(length = 50)
    var barcode: String? = null,

    /** 단위 (예: EA, BOX, SET 등) */
    @Column(length = 20)
    val uom: String? = null,

    /** 제품명 */
    @Column(nullable = false)
    var name: String,

    /** 설명 */
    @Column(length = 500)
    val description: String? = null,

    /** 기본 판매가 */
    @Column(precision = 15, scale = 2)
    val basePrice: BigDecimal = BigDecimal.ZERO,

    /** 범주 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "m_product_category_id")
    var category: ProductCategory? = null,

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL])
    var productOptionGroups: MutableList<ProductOptionGroup> = mutableListOf()
) : TsidBaseEntity<Product>() {
}