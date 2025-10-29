package com.dohyundev.pos.core.catalog.product.command.domain

enum class TaxType(
    val label: String
) {
    TAXABLE("과세"),
    NON_TAXABLE("비과세"),
    EXEMPT("면세"),
    ZERO_RATED("영세")
}