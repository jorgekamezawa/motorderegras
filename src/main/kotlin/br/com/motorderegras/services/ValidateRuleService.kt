package br.com.motorderegras.services

import br.com.motorderegras.domain.JoinEnum
import br.com.motorderegras.entities.toDomain
import br.com.motorderegras.form.AccrualFeesForm
import br.com.motorderegras.form.TesteForm
import br.com.motorderegras.form.ValidationForm
import br.com.motorderegras.repository.RuleRepository
import br.com.motorderegras.view.RuleView
import br.com.motorderegras.view.toView
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Service
import java.lang.reflect.Field

@Service
class ValidateRuleService(
    private val ruleRepository: RuleRepository
) {
    fun validateRuleGeneric(form: ValidationForm): RuleView? {
        val origin = form.origin ?: throw RuntimeException("Origin nao pode ser null")
        val ruleList = ruleRepository.findByOrigin(origin).map { it.toDomain() }
        if (ruleList.isEmpty()) throw RuntimeException("Nao foram encontradas regras para o origin = ${form.origin}")
        val finalRule = ruleList.filter { rule ->
            var joinDefinition: JoinEnum? = null
            var expressionDefinition: Boolean
            var lastExpressionDefinition = false
            var validacaoDefinition = false
            rule.definitionList
                .sortedBy { it.order }
                .forEach { definition ->
                    var joinCondition: JoinEnum? = null
                    var expressionCondition: Boolean
                    var lastExpressionCondition = false
                    var validacaoCondition = false
                    try {
                        definition.conditionList
                            .sortedBy { it.order }
                            .forEach { condition ->
                                val field = getValue(form, condition.field)
                                    ?: throw RuntimeException("Nao foi encontrado field")
                                expressionCondition = condition.operator.run(field, condition.value)
                                validacaoCondition = joinCondition?.run(lastExpressionCondition, expressionCondition)
                                    ?: expressionCondition
                                lastExpressionCondition = expressionCondition
                                joinCondition = condition.join
                            }
                        expressionDefinition = validacaoCondition
                    } catch (ex: RuntimeException) {
                        expressionDefinition = false
                    }
                    
                    validacaoDefinition =
                        joinDefinition?.run(lastExpressionDefinition, expressionDefinition) ?: expressionDefinition
                    lastExpressionDefinition = expressionDefinition
                    joinDefinition = definition.join
                }
            return@filter validacaoDefinition
        }
        
        if (finalRule.isEmpty()) throw RuntimeException("Nao encontrada nenhuma regra!")
        if (finalRule.size > 1) throw RuntimeException("Encontrada mais de uma regra!")
        
        return finalRule.first().toView()
    }
    
    private fun getValue(form: ValidationForm, fieldName: String): Any? {
        val field: Field = form::class.java.declaredFields.find { it.name == fieldName } ?: return null
        field.isAccessible = true
        return field.get(form)
    }
    
    fun validateRuleByString(formString: String, origin: String): RuleView? {
        val mapper = jacksonObjectMapper()
        val form: ValidationForm = try {
            mapper.readValue(formString, AccrualFeesForm::class.java)
        } catch (ex: Exception) {
            try {
                mapper.readValue(formString, TesteForm::class.java)
            } catch (ex: Exception) {
                throw RuntimeException("Erro na conversao para o Form!")
            }
        }
        form.origin = origin
        return validateRuleGeneric(form)
    }
}
