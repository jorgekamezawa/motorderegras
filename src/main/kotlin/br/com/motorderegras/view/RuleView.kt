package br.com.motorderegras.view

import br.com.motorderegras.domain.Definition
import br.com.motorderegras.domain.Rule

data class RuleView(
    val id: Long,
    val name: String,
    val origin: String,
    val priorityOrder: Int,
    val definitionList: List<Definition>
)

fun Rule.toView() =
    RuleView(
        id = this.id,
        origin = this.origin,
        name = this.name,
        priorityOrder = this.priorityOrder,
        definitionList = this.definitionList
    )
