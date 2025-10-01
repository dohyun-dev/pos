package com.dohyundev.pos.core.catalog.menu_group.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

interface MenuGroupCommand {
    data class Create(
        @field:NotBlank
        @field:Size(max = 100)
        val name: String,

        @field:Min(0)
        val priority: Int = 0,

        val isVisible: Boolean = true
    ) : MenuGroupCommand

    data class Update(
        @field:NotNull
        val menuGroupId: Long,

        @field:Size(max = 100)
        val name: String?,

        @field:Min(0)
        val priority: Int?,

        val isVisible: Boolean?
    ) : MenuGroupCommand

    data class Delete(
        @field:NotNull
        val menuGroupId: Long
    ) : MenuGroupCommand
}
