package com.dohyundev.pos.core.catalog.option_group.command.domain

import com.dohyundev.common.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "options")
class Option(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_group_id")
    var optionGroup: OptionGroup,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var price: Long = 0,

    @Column(nullable = false)
    var isDefault: Boolean = false,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var state: OptionState = OptionState.ON_SALE,

    @Column(nullable = false)
    val displayOrder: Int = 0
): BaseEntity() {
}