package br.com.motorderegras.form

import java.math.BigDecimal

class AccrualFeesForm(
    override var origin: String? = null,
    val motivo: String,
    val segmento: String,
    val valorInvestido: BigDecimal?
) : ValidationForm(
    origin = origin
)
