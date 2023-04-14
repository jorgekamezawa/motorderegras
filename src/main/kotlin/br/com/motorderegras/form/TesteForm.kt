package br.com.motorderegras.form

data class TesteForm(
    override var origin: String? = null,
    val nome: String,
    val sobrenome: String,
    val idade: Int
) : ValidationForm(
    origin = origin
)
