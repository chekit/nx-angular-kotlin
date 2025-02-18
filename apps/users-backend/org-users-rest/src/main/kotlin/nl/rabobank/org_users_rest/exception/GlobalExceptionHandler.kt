package nl.rabobank.org_users_rest.exception

import org.springframework.expression.ParseException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ResponseStatusException

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(ex: ResponseStatusException): ResponseEntity<ApiError> {
        val error = ApiError(
            status = ex.statusCode.value(),
            error = ex.body.detail ?: "",
            message = ex.reason ?: "Unknown error",
        )

        return ResponseEntity.status(ex.statusCode).body(error)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleParseException(ex: MethodArgumentNotValidException): ResponseEntity<ApiError> {
        val error = ApiError(
            status = ex.statusCode.value(),
            error = ex.body.detail ?: "",
            message = ex.bindingResult.fieldErrors.joinToString(", ") { it.defaultMessage.orEmpty() },
        )

        return ResponseEntity.status(ex.statusCode).body(error)
    }

}