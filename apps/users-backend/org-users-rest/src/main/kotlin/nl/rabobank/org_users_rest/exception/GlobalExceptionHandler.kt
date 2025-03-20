package nl.rabobank.org_users_rest.exception

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import nl.rabobank.org_users_rest.model.UserRole
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
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

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleInvalidEnumException(ex: HttpMessageNotReadableException): ResponseEntity<ApiError> {
        val cause = ex.cause;
        var error = ApiError(
            status = HttpStatus.BAD_REQUEST.value(),
            error = "Invalid request format",
            message = ""
        );

        if (cause is InvalidFormatException && cause.targetType.isEnum) {
            if (cause.targetType == UserRole::class.java) {
                error = ApiError(
                    status = HttpStatus.BAD_REQUEST.value(),
                    error = "Can't parse the data",
                    message = "Invalid role value"
                )
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error)
    }
}