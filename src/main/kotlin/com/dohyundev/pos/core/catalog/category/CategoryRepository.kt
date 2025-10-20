package com.dohyundev.pos.core.catalog.category

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CategoryRepository : JpaRepository<Category, Long> {
    @Query("select coalesce(max(c.displayOrder), 0)  from Category c")
    fun findMaxDisplayOrder(): Long
    
    @Query("select c from Category c where c.displayOrder < :displayOrder order by c.displayOrder desc limit 1")
    fun findPrevious(displayOrder: Long): Category?
    
    @Query("select c from Category c where c.displayOrder > :displayOrder order by c.displayOrder asc limit 1")
    fun findNext(displayOrder: Long): Category?
}
