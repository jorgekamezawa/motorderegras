package br.com.motorderegras.domain

data class Definition(
    val conditionList: List<Condition>,
    val join: JoinEnum? = null,
    val order: Int
)