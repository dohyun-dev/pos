package com.dohyundev.pos.core.catalog.product.command.exception

import com.dohyundev.common.exception.CustomEntityNotFoundException

class ProductEntityNotFoundException: CustomEntityNotFoundException(message = "상품을 찾을 수 없습니다. ") {
}