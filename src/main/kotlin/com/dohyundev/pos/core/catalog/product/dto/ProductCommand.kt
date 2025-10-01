package com.dohyundev.pos.core.catalog.product.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

interface ProductCommand {
    data class Create(
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
    ) : ProductCommand

    data class Update(
        @field:NotNull
        val productId: Long,

        @field:Size(max = 100)
        val name: String?,

        @field:Size(max = 500)
        val description: String?,

        @field:Min(0)
        val price: Long?,

        @field:Min(0)
        val priority: Int?
    ) : ProductCommand

    data class Delete(
        @field:NotNull
        val productId: Long
    ) : ProductCommand
}
