package com.dohyundev.pos.core.catalog.category.command.exception

import com.dohyundev.common.exception.CustomEntityNotFoundException

class CategoryEntityNotFoundException: CustomEntityNotFoundException(message = "카테고리를 찾을 수 없습니다. ") {
}