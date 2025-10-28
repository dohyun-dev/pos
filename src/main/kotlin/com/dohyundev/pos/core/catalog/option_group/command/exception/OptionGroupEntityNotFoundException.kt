package com.dohyundev.pos.core.catalog.option_group.command.exception

import com.dohyundev.common.exception.CustomEntityNotFoundException

class OptionGroupEntityNotFoundException: CustomEntityNotFoundException(message = "옵션그룹을 찾을 수 없습니다. ") {
}