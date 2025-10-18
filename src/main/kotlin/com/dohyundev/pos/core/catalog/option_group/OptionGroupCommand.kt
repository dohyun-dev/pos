package com.dohyundev.pos.core.catalog.option_group

import jakarta.validation.constraints.NotBlank

interface OptionGroupCommand {
    data class CreateProductOptionGroup(
        @field:NotBlank(message = "옵션 그룹 이름은 필수입니다.")
        val name: String? = null,
    ) {
    }

    data class CreateProductOption(
        @field:NotBlank(message = "옵션 그룹 이름은 필수입니다.")
        val name: String? = null,
    ) {
    }

    class UpdateOptionGroup {

    }

    class BulkUpdateOptionGroup {

    }
}