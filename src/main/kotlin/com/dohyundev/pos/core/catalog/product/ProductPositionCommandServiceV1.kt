package com.dohyundev.pos.core.catalog.product

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductPositionCommandServiceV1 {
    @Transactional
    fun exchangeProductPosition(command: ProductCommand.ExchangeProductPosition) {
    }

}
