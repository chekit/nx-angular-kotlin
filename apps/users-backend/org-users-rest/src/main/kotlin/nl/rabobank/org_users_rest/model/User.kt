package nl.rabobank.org_users_rest.model

import jakarta.validation.constraints.NotBlank

data class User(val id: Int, val firstName: String, val lastName: String, val role: UserRole)

data class UserDto(
    @field:NotBlank(message = "firstName should no be blank")
    val firstName: String,
    @field:NotBlank(message = "lastName should no be blank")
    val lastName: String,
    val role: UserRole = UserRole.USER
)

data class UpdateUserDto(val firstName: String?, val lastName: String?, val role: UserRole?)

enum class UserRole(val role: String) {
    ADMIN("admin"),
    MODERATOR("moderator"),
    USER("user")
}

// We can use sealed class to group different type of users in one class: https://kotlinlang.org/docs/sealed-classes.html#payment-method-handling