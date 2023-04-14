package br.com.motorderegras.controller

import br.com.motorderegras.form.RuleForm
import br.com.motorderegras.services.RuleService
import br.com.motorderegras.view.RuleView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rule")
class RuleController(
    private val ruleService: RuleService
) {
    
    @PostMapping
    fun create(@RequestBody form: RuleForm): ResponseEntity<RuleView> {
        return ResponseEntity.ok(ruleService.create(form))
    }
    
    @GetMapping
    fun getAll(): ResponseEntity<List<RuleView>> {
        return ResponseEntity.ok(ruleService.getAll())
    }
    
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<RuleView> {
        return ResponseEntity.ok(ruleService.getById(id))
    }
    
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody form: RuleForm): ResponseEntity<RuleView> {
        return ResponseEntity.ok(ruleService.update(form, id))
    }
}