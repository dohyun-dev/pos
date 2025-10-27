package com.dohyundev.pos.core.catalog.domain.repository

import com.dohyundev.pos.core.catalog.domain.entity.ProductOption
import org.springframework.data.jpa.repository.JpaRepository

interface ProductOptionRepository : JpaRepository<ProductOption, String> {
}