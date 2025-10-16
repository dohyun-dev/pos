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
    var uom: String? = null,

    /** 제품명 */
    @Column(nullable = false)
    var name: String,

    /** 설명 */
    @Column(length = 500)
    var description: String? = null,

    /** 기본 판매가 */
    @Column(precision = 15, scale = 2)
    var basePrice: BigDecimal = BigDecimal.ZERO,

    /** 범주 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id")
    var category: ProductCategory? = null,

    /** 옵션 그룹 매핑 */
    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    var optionGroupMappings: MutableList<ProductOptionGroupMapping> = mutableListOf()
) : TsidBaseEntity<Product>() {
    
    fun update(
        code: String,
        name: String,
        basePrice: BigDecimal,
        barcode: String? = null,
        description: String? = null,
        uom: String? = null,
        category: ProductCategory? = null,
        optionGroups: Collection<ProductOptionGroup> = emptyList()
    ) {
        this.code = code
        this.name = name
        this.basePrice = basePrice
        this.barcode = barcode
        this.description = description
        this.uom = uom
        this.category = category
        changeOptionGroupMappings(optionGroups)
    }

    /**
     * 옵션 그룹 매핑을 변경합니다.
     * 기존 매핑을 모두 제거하고 새로운 매핑으로 교체합니다.
     */
    fun changeOptionGroupMappings(optionGroups: Collection<ProductOptionGroup>) {
        this.optionGroupMappings.clear()
        this.optionGroupMappings.addAll(
            optionGroups.mapIndexed { index, group ->
                ProductOptionGroupMapping(
                    product = this,
                    optionGroup = group,
                    displayOrder = index.toLong()
                )
            }
        )
    }
}
