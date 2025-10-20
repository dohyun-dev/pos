package com.dohyundev.pos.core.catalog.category

import com.dohyundev.common.entity.Activatable
import com.dohyundev.common.entity.DisplayOrderable
import com.dohyundev.common.entity.TsidBaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.PrePersist
import jakarta.persistence.PreRemove
import jakarta.persistence.PreUpdate
import jakarta.persistence.Version

@Entity
class Category(
    @Column(nullable = false)
    var title: String,

    var description: String? = null,

    @Column(nullable = false)
    override var displayOrder: Long = 0,

    @Column(nullable = false)
    override var isActive: Boolean = true
) : TsidBaseEntity(), DisplayOrderable, Activatable{
    @Version
    private var version: Long? = null

    @PrePersist
    fun prePersist() {
        this.andEvent(CreateCategoryEvent(this))
    }

    @PreUpdate
    fun preUpdate() {
        this.andEvent(UpdateCategoryEvent(this))
    }


    @PreRemove
    fun preRemove() {
        this.andEvent(RemoveCategoryEvent(this))
    }

    fun update(
        title: String = this.title,
        description: String? = this.description,
        displayOrder: Long = this.displayOrder,
        isActive: Boolean = this.isActive
    ) {
        this.title = title
        this.description = description
        changeDisplayOrder(displayOrder)
        this.isActive = isActive
    }
}
