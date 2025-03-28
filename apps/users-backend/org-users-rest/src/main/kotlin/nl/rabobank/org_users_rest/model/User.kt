package nl.rabobank.org_users_rest.model

import jakarta.validation.constraints.NotBlank

data class UserDto(
    @field:NotBlank(message = "firstName should no be blank")
    val firstName: String,
    @field:NotBlank(message = "lastName should no be blank")
    val lastName: String,
    val role: UserRole = UserRole.USER
)

data class UserUpdateDto(val firstName: String?, val lastName: String?, val role: UserRole?)

// @TODO: make case insensitive
enum class UserRole(val role: String) {
    ADMIN("admin"),
    MODERATOR("moderator"),
    USER("user");
}