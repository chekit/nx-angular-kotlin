package nl.rabobank.org_users_rest.repository

import nl.rabobank.org_users_rest.entity.User

interface UsersRepository {
    fun findAll(): List<User>;
    fun findUserById(id: Int): User?;
    fun addOne(newUser: User): List<User>;
    fun updateOne(updatedUser: User): User;
    fun deleteOne(id: Int): String?;
    fun save(data: List<User>): String;
}