package com.dohyundev.pos.core.catalog.option_group

import com.dohyundev.common.entity.Activatable
import com.dohyundev.common.entity.DisplayOrderable
import com.dohyundev.common.entity.TsidBaseEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
class OptionGroup(
    @Column(nullable = false)
    var title: String,

    var description: String? = null,

    @Column(nullable = false)
    var isRequired: Boolean = false,

    @OneToMany(mappedBy = "group", cascade = [CascadeType.ALL], orphanRemoval = true)
    var options: MutableList<Option> = mutableListOf(),

    @Column(nullable = false)
    var minChoiceCount: Int = 0,

    @Column(nullable = false)
    var maxChoiceCount: Int = 1,

    @JdbcTypeCode(SqlTypes.JSON_ARRAY)
    @Column(name = "default_selected_Ids", nullable = false)
    var defaultSelectedIds: List<Long> = emptyList(),

    @Column(nullable = false)
    override var displayOrder: Long = 0,

    @Column(nullable = false)
    override var isActive: Boolean = true
) : TsidBaseEntity(), DisplayOrderable, Activatable {

    fun update(
        title: String = this.title,
        description: String? = this.description,
        isRequired: Boolean = this.isRequired,
        options: MutableList<Option> = this.options,
        minChoiceCount: Int = this.minChoiceCount,
        maxChoiceCount: Int = this.maxChoiceCount,
        defaultSelectedIds: List<Long> = this.defaultSelectedIds,
        displayOrder: Long = this.displayOrder,
        isActive: Boolean = this.isActive,
    ) {
        this.title = title
        this.description = description
        this.isRequired = isRequired
        this.options = options
        this.minChoiceCount = minChoiceCount
        this.maxChoiceCount = maxChoiceCount
        this.displayOrder = displayOrder
        changeOptions(options)
        this.isActive = isActive
    }

    fun changeOptions(options: Collection<Option>) {
        this.options.clear()
        this.options.addAll(options)
    }
}
