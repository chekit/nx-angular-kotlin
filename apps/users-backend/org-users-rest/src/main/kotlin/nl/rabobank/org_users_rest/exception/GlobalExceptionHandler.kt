package nl.rabobank.org_users_rest.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ResponseStatusException

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(ex: ResponseStatusException): ResponseEntity<ApiError> {
        val error = ApiError(
            status = ex.statusCode.value(),
            error = ex.titleMessageCode,
            message = ex.reason ?: "Unknown error",
        )

        return ResponseEntity.status(ex.statusCode).body(error)
    }
}