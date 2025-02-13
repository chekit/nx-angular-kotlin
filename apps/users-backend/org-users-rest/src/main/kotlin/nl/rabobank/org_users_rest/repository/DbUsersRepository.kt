package nl.rabobank.org_users_rest.repository

import nl.rabobank.org_users_rest.model.User

class DbUsersRepository: UsersRepository {
    override fun findAll(): MutableList<User> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): User? {
        TODO("Not yet implemented")
    }

    override fun addOne(newUser: User): MutableList<User> {
        TODO("Not yet implemented")
    }

    override fun updateOne(updatedUser: User): User {
        TODO("Not yet implemented")
    }

    override fun deleteOne(id: Int): String? {
        TODO("Not yet implemented")
    }

    override fun save(data: MutableList<User>): String {
        TODO("Not yet implemented")
    }
}