package com.dohyundev.pos.core.catalog.product

import com.dohyundev.common.vo.MoveDirection
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductPositionCommandServiceV1(
    private val productRepository: ProductRepository,
    private val productPositionRepository: ProductPositionRepository
) {
    @Transactional
    fun exchangeProductPosition(command: ProductCommand.ExchangeProductPosition) {
        val product = productRepository.findByIdOrNull(command.productId)
            ?: throw EntityNotFoundException("제품을 찾을 수 없습니다. ID: ${command.productId}")
        
        val currentPosition = productPositionRepository.findByProduct(product)
            ?: throw EntityNotFoundException("제품 위치 정보를 찾을 수 없습니다. Product ID: ${command.productId}")
        
        val targetPosition = when (command.direction) {
            MoveDirection.UP -> productPositionRepository.findPrevious(currentPosition.position)
            MoveDirection.DOWN -> productPositionRepository.findNext(currentPosition.position)
        } ?: throw IllegalStateException("이동할 수 없는 위치입니다.")
        
        // 위치 교환
        val tempPosition = currentPosition.position
        currentPosition.position = targetPosition.position
        targetPosition.position = tempPosition
    }
}
