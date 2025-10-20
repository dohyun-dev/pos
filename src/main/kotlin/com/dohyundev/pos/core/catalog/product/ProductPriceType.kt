package com.dohyundev.pos.core.catalog.product

enum class ProductPriceType {
    FIXED,      // 고정가
    VARIABLE,   // 수동입력가 (POS에서 직접 입력)
    UNIT        // 단위당 가격 (예: 100g당 2500원)
}
