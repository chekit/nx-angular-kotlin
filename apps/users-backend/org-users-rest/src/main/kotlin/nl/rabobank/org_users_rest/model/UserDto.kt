package nl.rabobank.org_users_rest.model

data class UserDto(val firstName: String, val lastName: String, val role: UserRole)
data class UpdateUserDto(val firstName: String?, val lastName: String?, val role: UserRole?)
