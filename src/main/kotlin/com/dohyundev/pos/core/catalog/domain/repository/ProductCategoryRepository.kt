package com.dohyundev.pos.core.catalog.domain.repository

import com.dohyundev.pos.core.catalog.domain.entity.ProductCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductCategoryRepository : JpaRepository<ProductCategory, String> {
    @Query("select max(c.displayOrder) from ProductCategory c")
    fun findMaxDisplayOrder(): Long
    
    @Query("select c from ProductCategory c where c.displayOrder < :displayOrder order by c.displayOrder desc limit 1")
    fun findPrevious(displayOrder: Long): ProductCategory?
    
    @Query("select c from ProductCategory c where c.displayOrder > :displayOrder order by c.displayOrder asc limit 1")
    fun findNext(displayOrder: Long): ProductCategory?
}
