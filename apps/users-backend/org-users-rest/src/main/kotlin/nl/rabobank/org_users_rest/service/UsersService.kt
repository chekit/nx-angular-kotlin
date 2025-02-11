package nl.rabobank.org_users_rest.service

import nl.rabobank.org_users_rest.model.UpdateUserDto
import nl.rabobank.org_users_rest.model.User
import nl.rabobank.org_users_rest.model.UserDto
import nl.rabobank.org_users_rest.repository.UsersRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UsersService(private val repo: UsersRepository) {
    fun getUsers(): MutableList<User> {
        return repo.findAll()
    }

    fun addUser(data: UserDto): MutableList<User> {
        val users: MutableList<User> = repo.findAll();
        val lastId: Int = users.maxByOrNull { it.id }?.let { it.id + 1 } ?: 0;
        val newUser = User(lastId, data.firstName, data.lastName, data.role);

        return repo.addOne(newUser);
    }

    fun updateUserById(id: Int, data: UpdateUserDto): User {
        val userIndex = repo.findIndexById(id)

        if (userIndex < 0) throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID $id not found");

        val users: MutableList<User> = repo.findAll();
        val user = users[userIndex];
        val updatedUser = User(
            id = user.id,
            firstName = data.firstName ?: user.firstName,
            lastName = data.lastName ?: user.lastName,
            role = data.role ?: user.role
        )

        return repo.updateOne(userIndex, updatedUser);
    }

    fun deleteUserById(id: Int): String {
        val userIndex = repo.findIndexById(id);

        if (userIndex < 0) throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID $id not found");

        return repo.deleteOne(userIndex)
    }
}