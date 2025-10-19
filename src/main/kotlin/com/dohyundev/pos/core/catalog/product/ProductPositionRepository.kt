package com.dohyundev.pos.core.catalog.product

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductPositionRepository : JpaRepository<ProductPosition, String> {
    fun findByProduct(product: Product): ProductPosition?
    
    @Query("select pp from ProductPosition pp where pp.position < :position order by pp.position desc limit 1")
    fun findPrevious(position: Long): ProductPosition?
    
    @Query("select pp from ProductPosition pp where pp.position > :position order by pp.position asc limit 1")
    fun findNext(position: Long): ProductPosition?
}
