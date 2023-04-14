package br.com.motorderegras.controller.handler.view

data class ErrorView(
    val httpStatus: String,
    val message: String?,
    val dateTime: String
)