package com.dohyundev.pos.core.catalog.option_group

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface OptionGroupMappingRepository : JpaRepository<OptionGroupItem, String> {
    
    /**
     * 특정 상품의 모든 옵션 그룹 매핑을 조회합니다.
     */
    @Query("SELECT m FROM OptionGroupItem m WHERE m.product.id = :productId ORDER BY m.displayOrder")
    fun findByProductIdOrderByDisplayOrder(productId: String): List<OptionGroupItem>
    
    /**
     * 특정 옵션 그룹의 모든 상품 매핑을 조회합니다.
     */
    @Query("SELECT m FROM OptionGroupItem m WHERE m.optionGroup.id = :optionGroupId ORDER BY m.displayOrder")
    fun findByOptionGroupIdOrderByDisplayOrder(optionGroupId: String): List<OptionGroupItem>
    
    /**
     * 특정 상품과 옵션 그룹의 매핑을 조회합니다.
     */
    @Query("SELECT m FROM OptionGroupItem m WHERE m.product.id = :productId AND m.optionGroup.id = :optionGroupId")
    fun findByProductIdAndOptionGroupId(productId: String, optionGroupId: String): OptionGroupItem?
    
    /**
     * 특정 상품의 모든 매핑을 삭제합니다.
     */
    fun deleteByProductId(productId: String)
    
    /**
     * 특정 옵션 그룹의 모든 매핑을 삭제합니다.
     */
    fun deleteByOptionGroupId(optionGroupId: String)
}
