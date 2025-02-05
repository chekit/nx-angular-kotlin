package nl.rabobank.org_users_rest.model

data class User(val id: Int, val firstName: String, val lastName: String, val role: UserRole)

enum class UserRole(val role: String) {
    ADMIN("admin"),
    MODERATOR("moderator"),
    USER("user")
}