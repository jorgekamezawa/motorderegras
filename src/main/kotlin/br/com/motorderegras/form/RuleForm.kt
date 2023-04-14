package br.com.motorderegras.form

import br.com.motorderegras.domain.Definition
import br.com.motorderegras.domain.Rule

data class RuleForm(
    val name: String,
    val origin: String,
    val priorityOrder: Int,
    val definitionList: List<Definition>
)

fun RuleForm.toDomain() =
    Rule(
        id = 0L,
        name = this.name,
        origin = this.origin,
        priorityOrder = this.priorityOrder,
        definitionList = this.definitionList
    )

fun RuleForm.toDomain(id: Long) =
    Rule(
        id = id,
        name = this.name,
        origin = this.origin,
        priorityOrder = this.priorityOrder,
        definitionList = this.definitionList
    )
