package br.com.motorderegras.controller.handler

import br.com.motorderegras.controller.handler.view.ErrorView
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@ControllerAdvice
class ExceptionHandler {
    
    @ExceptionHandler(value = [RuntimeException::class])
    fun handlerRuntimeException(ex: RuntimeException): ResponseEntity<ErrorView> {
        val status = HttpStatus.BAD_REQUEST
        val errorView = ErrorView(
            httpStatus = "${status.value()} ${status.name}",
            message = ex.message,
            dateTime = localDateTimeToString()
        )
        return ResponseEntity(errorView, status)
    }
    
    private fun localDateTimeToString(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss"))
    }
}