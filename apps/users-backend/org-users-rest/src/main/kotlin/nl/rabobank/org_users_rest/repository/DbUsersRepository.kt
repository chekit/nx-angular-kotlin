package nl.rabobank.org_users_rest.repository

import nl.rabobank.org_users_rest.model.User
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository

//@Repository
//@Profile("dev")
class DbUsersRepository: UsersRepository {
    override fun findAll(): MutableList<User> {
        TODO("Not yet implemented")
    }

    override fun addOne(newUser: User): MutableList<User> {
        TODO("Not yet implemented")
    }

    override fun updateOne(index: Int, updatedUser: User): User {
        TODO("Not yet implemented")
    }

    override fun deleteOne(index: Int): String {
        TODO("Not yet implemented")
    }

    override fun save(data: MutableList<User>): String {
        TODO("Not yet implemented")
    }

    override fun findIndexById(id: Int): Int {
        TODO("Not yet implemented")
    }
}