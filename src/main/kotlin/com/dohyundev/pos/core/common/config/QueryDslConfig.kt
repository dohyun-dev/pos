package com.dohyundev.pos.core.common.config

import com.querydsl.jpa.DefaultQueryHandler
import com.querydsl.jpa.Hibernate5Templates
import com.querydsl.jpa.QueryHandler
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueryDslConfig(
    @PersistenceContext
    private val entityManager: EntityManager
) {

    @Bean
    fun queryFactory(): JPAQueryFactory {
        return JPAQueryFactory(CustomHibernate5Templates(), entityManager)
    }

    class CustomHibernate5Templates : Hibernate5Templates() {
        override fun getQueryHandler(): QueryHandler {
            return DefaultQueryHandler.DEFAULT
        }
    }
}