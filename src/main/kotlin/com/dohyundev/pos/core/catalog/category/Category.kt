package com.dohyundev.pos.core.catalog.category

import com.dohyundev.common.entity.Activatable
import com.dohyundev.common.entity.DisplayOrderable
import com.dohyundev.common.entity.TsidBaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.PreRemove
import jakarta.persistence.Version

@Entity
class Category(
    @Column(nullable = false)
    var name: String,

    var description: String? = null,

    @Column(nullable = false)
    override var displayOrder: Long = 0,

    @Column(nullable = false)
    override var isActive: Boolean = true
) : TsidBaseEntity(), DisplayOrderable, Activatable{
    @Version
    private var version: Long? = null

    @PreRemove
    fun preRemove() {
        this.andEvent(RemoveCategoryEvent(this))
    }

    fun update(name: String, description: String?) {
        this.name = name
        this.description = description
    }
}
