package br.com.motorderegras.domain

data class Rule(
        val id: Long = 0,
        val name: String,
        val origin: String,
        val priorityOrder: Int,
        val definitionList: List<Definition>
)

