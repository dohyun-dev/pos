package com.dohyundev.pos.core.catalog.domain.repository

import com.dohyundev.pos.core.catalog.domain.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, String> {
    fun existsByCode(code: String): Boolean
}