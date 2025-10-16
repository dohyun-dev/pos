package com.dohyundev.common.entity

interface Activatable {
    var isActive: Boolean

    fun activate() {
        this.isActive = true
    }

    fun deactivate() {
        this.isActive = false
    }

    fun toggleActive() {
        this.isActive = !this.isActive
    }

    fun changeActive(active: Boolean) {
        this.isActive = active
    }
}