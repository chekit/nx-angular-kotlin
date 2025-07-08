package nl.rabobank.org_users_rest.exception

import org.hibernate.query.sqm.UnknownEntityException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ResponseStatusException
import java.util.NoSuchElementException

/** @TODO
 *     Kotlin has "result" response with sealed class
 *     We can create a nice object instead of throwing error or not just throw exceptions
 *     https://arrow-kt.io/learn/typed-errors/
 */
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

    @ExceptionHandler(NoSuchElementException::class)
    fun handleInvalidEnumException(ex: NoSuchElementException): ResponseEntity<ApiError> {
        val cause = ex.cause;
        val error = ApiError(
            status = HttpStatus.BAD_REQUEST.value(),
            error = "Invalid request format",
            message = "Sent data is incorrect. Please check your input format.",
        );

        return ResponseEntity.badRequest().body(error)
    }

    @ExceptionHandler(UnknownEntityException::class)
    fun handleInvalidDbQuery(ex: UnknownEntityException): ResponseEntity<ApiError> {
        val error = ApiError(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = "Source unavailable",
            message = "Can't reach the data source"
        );

        return ResponseEntity.internalServerError().body(error)
    }
}