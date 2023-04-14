package br.com.motorderegras.repository

import br.com.motorderegras.entities.RuleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RuleRepository : JpaRepository<RuleEntity, Long> {
    fun findByOrigin(origin: String): List<RuleEntity>
}