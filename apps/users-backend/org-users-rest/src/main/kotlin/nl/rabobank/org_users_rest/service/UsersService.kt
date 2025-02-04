package nl.rabobank.org_users_rest.service

import nl.rabobank.org_users_rest.repository.FsUsersRepository
import org.springframework.stereotype.Service

@Service
class UsersService(private val repo: FsUsersRepository) {
    fun getUsers(): String? {
        return repo.data
    }

    fun addUser(data: String): String? {
        return "You added $data";
    }

    fun updateUser(id: String): String? {
        return "You updated $id";
    }

    fun deleteUser(id: String): String? {
        return "You deleted $id";
    }
}