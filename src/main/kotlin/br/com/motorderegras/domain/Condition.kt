package br.com.motorderegras.domain

data class Condition(
        val field: String,
        val operator: OperatorEnum,
        val value: String,
        val join: JoinEnum? = null,
        val order: Int
)