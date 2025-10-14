package com.dohyundev.pos.core.catalog.domain.entity

import com.dohyundev.common.entity.TsidBaseEntity
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
class ProductOption(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "m_option_group_id")
    var group: ProductOptionGroup,

    /** 옵션명 (예: 빨강, 파랑, L, 치즈추가 등) */
    @Column(nullable = false)
    var name: String,

    /** 옵션 설명 */
    var description: String? = null,

    /** 추가금 */
    @Column(precision = 15, scale = 2)
    var extraPrice: BigDecimal = BigDecimal.ZERO,

    /** 기본 선택 여부 (초기 체크 상태) */
    var defaultSelected: Boolean = false,

    var displayOrder: Int = 0,
) : TsidBaseEntity<ProductOption>() {
    fun update(
        name: String,
        extraPrice: BigDecimal?,
        defaultSelected: Boolean? = false,
        description: String? = null,
        displayOrder: Int? = null
    ) {
        this.name = name
        this.extraPrice = extraPrice ?: this.extraPrice
        this.defaultSelected = defaultSelected ?: this.defaultSelected
        this.description = description
        this.displayOrder = displayOrder ?: this.displayOrder
    }
}
