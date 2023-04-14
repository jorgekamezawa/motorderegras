package br.com.motorderegras.services

import br.com.motorderegras.entities.toDomain
import br.com.motorderegras.entities.toEntity
import br.com.motorderegras.form.RuleForm
import br.com.motorderegras.form.toDomain
import br.com.motorderegras.repository.RuleRepository
import br.com.motorderegras.view.RuleView
import br.com.motorderegras.view.toView
import org.springframework.stereotype.Service

@Service
class RuleService(
    private val ruleRepository: RuleRepository
) {
    
    fun create(form: RuleForm): RuleView {
        var domain = form.toDomain()
        val entity = ruleRepository.save(domain.toEntity())
        domain = entity.toDomain()
        return domain.toView()
    }
    
    fun getAll(): List<RuleView> {
        return ruleRepository.findAll().map { it.toDomain().toView() }
    }
    
    fun getById(id: Long): RuleView {
        val ruleById = ruleRepository.findById(id)
        if (ruleById.isEmpty) throw RuntimeException("Rule nao encontrada pelo id = $id")
        return ruleById.get().toDomain().toView()
    }
    
    fun update(form: RuleForm, id: Long): RuleView {
        val ruleById = ruleRepository.findById(id)
        if (ruleById.isEmpty) throw RuntimeException("Rule nao encontrada pelo id = $id")
        return ruleRepository.save(form.toDomain(id).toEntity()).toDomain().toView()
    }
}