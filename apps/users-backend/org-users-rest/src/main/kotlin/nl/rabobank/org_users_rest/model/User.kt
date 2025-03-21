package nl.rabobank.org_users_rest.model

import jakarta.validation.constraints.NotBlank

sealed class User {
    data class Entity(val id: Int, val firstName: String, val lastName: String, val role: UserRole) : User()

    data class Dto(
        @field:NotBlank(message = "firstName should no be blank")
        val firstName: String,
        @field:NotBlank(message = "lastName should no be blank")
        val lastName: String,
        val role: UserRole = UserRole.USER
    ) : User()

    data class UpdateDto(val firstName: String?, val lastName: String?, val role: UserRole?) : User()
}

enum class UserRole(val role: String) {
    ADMIN("admin"),
    MODERATOR("moderator"),
    USER("user");
}