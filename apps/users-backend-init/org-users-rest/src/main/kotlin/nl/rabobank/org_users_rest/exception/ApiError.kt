package nl.rabobank.org_users_rest.exception

data class ApiError(
    val status: Int,
    val error: String,
    val message: String,
    val timestamp: String = java.time.Instant.now().toString()
) {
    fun update(error: String? = null, message: String? = null, status: Int? = null) =
        this.copy(
            error = error ?: this.error,
            message = message ?: this.message,
            status = status ?: this.status,
            timestamp = java.time.Instant.now().toString()
        );
}