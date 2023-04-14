package br.com.motorderegras.controller

import br.com.motorderegras.form.AccrualFeesForm
import br.com.motorderegras.form.TesteForm
import br.com.motorderegras.services.ValidateRuleService
import br.com.motorderegras.view.RuleView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/validate_rule")
class ValidateRuleController(
    private val validateRuleService: ValidateRuleService
) {
    
    @PostMapping("/teste")
    fun validatedTeste(
        @RequestHeader origin: String,
        @RequestBody form: TesteForm
    ): ResponseEntity<RuleView> {
        form.origin = origin
        return ResponseEntity.ok(validateRuleService.validateRuleGeneric(form))
    }

    @PostMapping("/accrualfees")
    fun validatedAccrualFees(
        @RequestHeader origem: String?,
        @RequestBody form: AccrualFeesForm
    ): ResponseEntity<RuleView> {
        form.origin = origem
        return ResponseEntity.ok(validateRuleService.validateRuleGeneric(form))
    }
    
    @PostMapping("/default")
    fun validateDefault(
        @RequestHeader origin: String,
        @RequestBody form: String
    ): ResponseEntity<RuleView> {
        return ResponseEntity.ok(validateRuleService.validateRuleByString(form, origin))
    }
}