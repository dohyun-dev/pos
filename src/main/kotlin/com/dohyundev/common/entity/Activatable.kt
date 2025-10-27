package com.dohyundev.common.entity

interface Activatable {
    var active: Boolean

    fun activate() {
        this.active = true
    }

    fun deactivate() {
        this.active = false
    }

    fun toggleActive() {
        this.active = !this.active
    }
}
