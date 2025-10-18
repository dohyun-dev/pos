package com.dohyundev.pos.core.catalog.option_group

import com.dohyundev.common.entity.TsidBaseEntity
import com.dohyundev.pos.core.catalog.product.Product
import jakarta.persistence.*

/**
 * Product와 ProductOptionGroup 간의 다대다 관계를 위한 매핑 테이블 엔티티
 */
@Entity
@Table(
    name = "product_option_group_mapping",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["product_id", "product_option_group_id"])
    ]
)
class OptionGroupItem(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    var product: Product,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_option_group_id", nullable = false)
    var optionGroup: OptionGroup,

    /** 표시 순서 */
    @Column(nullable = false)
    var displayOrder: Long = 0
) : TsidBaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OptionGroupItem) return false
        if (!super.equals(other)) return false

        if (product != other.product) return false
        if (optionGroup != other.optionGroup) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + product.hashCode()
        result = 31 * result + optionGroup.hashCode()
        return result
    }
}
