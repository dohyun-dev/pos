package com.dohyundev.pos.core.catalog.product

import com.dohyundev.pos.core.catalog.category.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductRepository: JpaRepository<Product, Long> {
    fun findAllByCategory(category: Category): List<Product>
    fun existsByCategory(category: Category): Boolean
}