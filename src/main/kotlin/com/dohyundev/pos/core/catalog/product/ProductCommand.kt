package com.dohyundev.pos.core.catalog.product

interface ProductCommand {
    data class CreateProduct(
        val name: String?
    ) {

    }


    data class UpdateProduct(
        val name: String?,
    ) {

    }

    data class ExchangeProductPosition(
        val any: Any
    ) {

    }

}