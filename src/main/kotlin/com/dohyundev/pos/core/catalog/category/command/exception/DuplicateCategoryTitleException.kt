package com.dohyundev.pos.core.catalog.category.command.exception

import com.dohyundev.common.exception.BusinessException

class DuplicateCategoryTitleException: BusinessException(message = "중복되는 이름의 카테고리가 있습니다.")