package nl.rabobank.org_users_rest.service

import nl.rabobank.org_users_rest.entity.User
import nl.rabobank.org_users_rest.model.AddUserDto
import nl.rabobank.org_users_rest.model.UpdateUserDto
import nl.rabobank.org_users_rest.model.UserDto
import nl.rabobank.org_users_rest.repository.DbUsersRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UsersService(private val usersRepo: DbUsersRepository, private val roleService: RolesService) {
    fun getUsers(): List<UserDto> = usersRepo.findAll()
        .map { UserDto(it) }
        .toList();


    fun getUserById(id: Int): UserDto = usersRepo.findById(id)
        .map { UserDto(it) }
        .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID $id not found") }


    fun addUser(data: AddUserDto): List<UserDto> {
        val newUser = User(
            firstName = data.firstName,
            lastName = data.lastName,
            role = roleService.getRoleById(data.role) ?: throw ResponseStatusException(
                HttpStatus.I_AM_A_TEAPOT,
                "User role is incorrect"
            )
        )

        usersRepo.save(newUser)

        return getUsers()
    }

    fun updateUserById(id: Int, data: UpdateUserDto): UserDto {
        val user = usersRepo.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID $id not found") }

        val result = usersRepo.save(
            User(
                id = user.id,
                firstName = data.firstName ?: user.firstName,
                lastName = data.lastName ?: user.lastName,
                role = data.role?.let {
                    roleService.getRoleById(data.role) ?: throw ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User role is incorrect"
                    )
                } ?: user.role
            ))

        return UserDto(result)
    }

    fun deleteUserById(id: Int): String = getUserById(id)
        .let { usersRepo.deleteById(it.id) }
        .let { "OK" };

}