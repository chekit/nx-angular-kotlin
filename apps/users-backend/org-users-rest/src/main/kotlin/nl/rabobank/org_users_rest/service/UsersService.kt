package nl.rabobank.org_users_rest.service

import nl.rabobank.org_users_rest.entity.User
import nl.rabobank.org_users_rest.model.AddUserDto
import nl.rabobank.org_users_rest.model.UserDto
import nl.rabobank.org_users_rest.model.UpdateUserDto
import nl.rabobank.org_users_rest.repository.DbRoleRepository
import nl.rabobank.org_users_rest.repository.DbUsersRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UsersService(private val usersRepo: DbUsersRepository, private val roleRepo: DbRoleRepository) {
    fun getUsers(): List<UserDto> {
        return usersRepo.findAll().map { UserDto(it) }.toList();
    }

    fun getUser(id: Int): UserDto {
        return usersRepo.findById(id)
            .map { UserDto(it) }
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID $id not found") }
    }

    fun addUser(data: AddUserDto): List<UserDto> {
        val users: List<UserDto> = getUsers();
        val lastId: Int = users.maxByOrNull { it.id }?.let { it.id + 1 } ?: 0;
        val newUser = User(firstName = data.firstName, lastName = data.lastName, role = roleRepo.findById(data.role).orElseThrow());

        usersRepo.save(newUser)

        return getUsers()
    }

    fun updateUserById(id: Int, data: UpdateUserDto): UserDto {
        val user = usersRepo.findById(id).orElseThrow();

        val updatedUser = User(
            id = user.id,
            firstName = data.firstName ?: user.firstName,
            lastName = data.lastName ?: user.lastName,
            role = data.role?.let { roleRepo.findById(data.role).orElseThrow()  } ?: user.role
        )

        return UserDto(usersRepo.save(updatedUser));
    }

    fun deleteUserById(id: Int): String {
        return getUser(id).let { usersRepo.deleteById(it.id) }.let { "OK" };
    }
}