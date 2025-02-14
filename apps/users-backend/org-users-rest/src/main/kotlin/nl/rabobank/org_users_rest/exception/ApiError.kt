package nl.rabobank.org_users_rest.exception

data class ApiError (
    val status: Int,
    val error: String,
    val message: String,
    val timestamp: String = java.time.Instant.now().toString()
)