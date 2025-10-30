package com.dohyundev.pos.core.catalog.product.command.application

interface ProductCommand {
    open class UpsertProduct()

    class BulkUpsertProduct(
        val id: Long?,
    )
}