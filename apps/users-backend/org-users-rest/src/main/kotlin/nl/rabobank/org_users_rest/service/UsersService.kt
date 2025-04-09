package nl.rabobank.org_users_rest.service

import nl.rabobank.org_users_rest.entity.User
import nl.rabobank.org_users_rest.model.UserDto
import nl.rabobank.org_users_rest.model.UserUpdateDto
import nl.rabobank.org_users_rest.repository.DbUsersRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import kotlin.jvm.optionals.getOrDefault

@Service
class UsersService(private val repo: DbUsersRepository) {
    fun getUsers(): List<User> {
        return repo.findAll().toList();
    }

    fun getUser(id: Int): User {
        return repo.findById(id)
            .getOrDefault(throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID $id not found"))
    }

    fun addUser(data: UserDto): List<User> {
        val users: List<User> = getUsers();
        val lastId: Int = users.maxByOrNull { it.id }?.let { it.id + 1 } ?: 0;
        val newUser = User(lastId, data.firstName, data.lastName, data.role.toString());

        return repo.save(newUser).let { getUsers() };
    }

    fun updateUserById(id: Int, data: UserUpdateDto): User {
        val user = getUser(id)

        val updatedUser = user.copy(
            firstName = data.firstName ?: user.firstName,
            lastName = data.lastName ?: user.lastName,
            role = (data.role ?: user.role).toString()
        );

        return repo.save(updatedUser);
    }

    fun deleteUserById(id: Int): String {
        return getUser(id).let { repo.deleteById(it.id) }.let { "OK" };
    }
}