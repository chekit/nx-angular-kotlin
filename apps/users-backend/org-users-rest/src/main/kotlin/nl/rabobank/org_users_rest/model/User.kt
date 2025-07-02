package nl.rabobank.org_users_rest.model

import jakarta.validation.constraints.NotBlank
import nl.rabobank.org_users_rest.entity.User

data class UserDto(
    @field:NotBlank(message = "firstName should no be blank")
    val firstName: String,
    @field:NotBlank(message = "lastName should no be blank")
    val lastName: String,
    val role: Int = 0
) {}

data class UserDto_2(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val role: String
) {
    constructor(user: User) : this(
        user.id,
        user.firstName,
        user.lastName,
        user.role.name
    )
}

data class UserUpdateDto(val firstName: String?, val lastName: String?, val role: Int?)

// @TODO: make case insensitive
// @deprecated
enum class UserRole(val role: String) {
    ADMIN("admin"),
    MODERATOR("moderator"),
    USER("user");
}