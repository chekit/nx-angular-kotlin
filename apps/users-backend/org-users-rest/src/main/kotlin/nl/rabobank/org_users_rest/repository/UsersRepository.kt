package nl.rabobank.org_users_rest.repository

import nl.rabobank.org_users_rest.model.User

interface UsersRepository {
    fun findAll(): MutableList<User>;
    fun findById(id: Int): User?;
    fun addOne(newUser: User): MutableList<User>;
    fun updateOne(updatedUser: User): User;
    fun deleteOne(id: Int): String?;
    fun save(data: MutableList<User>): String;
}