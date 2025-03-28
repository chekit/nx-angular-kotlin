package nl.rabobank.org_users_rest.service

import nl.rabobank.org_users_rest.entity.User
import nl.rabobank.org_users_rest.model.UserDto
import nl.rabobank.org_users_rest.model.UserUpdateDto
import nl.rabobank.org_users_rest.repository.UsersRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UsersService(private val repo: UsersRepository) {
    fun getUsers(): List<User> {
        return repo.findAll()
    }

    fun getUser(id: Int): User {
        return repo.findUserById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID $id not found");
    }

    fun addUser(data: UserDto): List<User> {
        val users: List<User> = repo.findAll();
        val lastId: Int = users.maxByOrNull { it.id }?.let { it.id + 1 } ?: 0;
        val newUser = User(lastId, data.firstName, data.lastName, data.role.toString());

        return repo.addOne(newUser);
    }

    fun updateUserById(id: Int, data: UserUpdateDto): User {
        val user =
            repo.findUserById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID $id not found");

        val updatedUser =  user.copy(
            firstName = data.firstName ?: user.firstName,
            lastName = data.lastName ?: user.lastName,
            role = (data.role ?: user.role).toString()
        );

        return repo.updateOne(updatedUser);
    }

    fun deleteUserById(id: Int): String {
        return repo.deleteOne(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID $id not found")
    }
}