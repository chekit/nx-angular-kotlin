package nl.rabobank.org_users_rest.service

import nl.rabobank.org_users_rest.model.User
import nl.rabobank.org_users_rest.repository.UsersRepository
import org.springframework.stereotype.Service

@Service
class UsersService(private val repo: UsersRepository) {
    fun getUsers(): MutableList<User> {
        return repo.getAll()
    }

    fun addUser(data: String): MutableList<User> {
        return repo.addOne()
    }

    fun updateUser(id: String): User {
        return repo.updateOne()
    }

    fun deleteUser(id: String): String {
        return repo.deleteOne()
    }
}