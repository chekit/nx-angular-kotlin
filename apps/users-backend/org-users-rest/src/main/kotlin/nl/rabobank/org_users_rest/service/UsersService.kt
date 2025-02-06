package nl.rabobank.org_users_rest.service

import nl.rabobank.org_users_rest.model.UpdateUserDto
import nl.rabobank.org_users_rest.model.User
import nl.rabobank.org_users_rest.model.UserDto
import nl.rabobank.org_users_rest.repository.UsersRepository
import org.springframework.stereotype.Service

@Service
class UsersService(private val repo: UsersRepository) {
    fun getUsers(): MutableList<User> {
        return repo.findAll()
    }

    fun addUser(data: UserDto): MutableList<User> {
        val users: MutableList<User> = repo.findAll();
        val lastId: Int = users.maxByOrNull { it.id }?.let { it.id + 1 } ?: 0;
        val newUser = User(lastId, data.firstName, data.lastName, data.role);

        users.addLast(newUser)
        repo.save(users);

        return users;
    }

    fun updateUserById(id: Int, data: UpdateUserDto): User {
        return repo.updateOne()
    }

    fun deleteUserById(id: Int): String {
        return repo.deleteOne()
    }
}