package nl.rabobank.org_users_rest.repository

import nl.rabobank.org_users_rest.model.User

class DbUsersRepository : UsersRepository {
    override fun findAll(): MutableList<User.Entity> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): User.Entity? {
        TODO("Not yet implemented")
    }

    override fun addOne(newUser: User.Entity): MutableList<User.Entity> {
        TODO("Not yet implemented")
    }

    override fun updateOne(updatedUser: User.Entity): User.Entity {
        TODO("Not yet implemented")
    }

    override fun deleteOne(id: Int): String? {
        TODO("Not yet implemented")
    }

    override fun save(data: MutableList<User.Entity>): String {
        TODO("Not yet implemented")
    }

}