package com.dohyundev.pos.core.catalog.option.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

interface OptionCommand {
    data class Create(
        @field:NotNull
        val optionGroupId: Long,

        @field:NotBlank
        @field:Size(max = 100)
        val name: String,

        @field:Size(max = 500)
        val description: String?,

        @field:NotNull
        @field:Min(0)
        val price: Long,

        @field:Min(0)
        val priority: Int = 0
    ) : OptionCommand

    data class Update(
        @field:NotNull
        val optionId: Long,

        @field:Size(max = 100)
        val name: String?,

        @field:Size(max = 500)
        val description: String?,

        @field:Min(0)
        val price: Long?,

        @field:Min(0)
        val priority: Int?
    ) : OptionCommand

    data class Delete(
        @field:NotNull
        val optionId: Long
    ) : OptionCommand
}
