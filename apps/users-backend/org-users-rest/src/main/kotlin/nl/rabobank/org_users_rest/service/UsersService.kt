package nl.rabobank.org_users_rest.service

import nl.rabobank.org_users_rest.entity.User
import nl.rabobank.org_users_rest.model.UserDto
import nl.rabobank.org_users_rest.model.UserDto_2
import nl.rabobank.org_users_rest.model.UserUpdateDto
import nl.rabobank.org_users_rest.repository.DbRoleRepository
import nl.rabobank.org_users_rest.repository.DbUsersRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UsersService(private val usersRepo: DbUsersRepository, private val roleRepo: DbRoleRepository) {
    fun getUsers(): List<UserDto_2> {
        return usersRepo.findAll().map { UserDto_2(it) }.toList();
    }

    fun getUser(id: Int): User {
        return usersRepo.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID $id not found") }
    }

    fun addUser(data: UserDto): List<UserDto_2> {
        val users: List<UserDto_2> = getUsers();
        val lastId: Int = users.maxByOrNull { it.id }?.let { it.id + 1 } ?: 0;
        val newUser = User(firstName = data.firstName, lastName = data.lastName, role = roleRepo.findById(data.role).orElseThrow());

        usersRepo.save(newUser)

        return getUsers()
    }

    fun updateUserById(id: Int, data: UserUpdateDto): User {
        val user = getUser(id)

        val updatedUser = user.copy(
            firstName = data.firstName ?: user.firstName,
            lastName = data.lastName ?: user.lastName,
//            role = data.role ?: user.role
        );

        return usersRepo.save(updatedUser);
    }

    fun deleteUserById(id: Int): String {
        return getUser(id).let { usersRepo.deleteById(it.id) }.let { "OK" };
    }
}