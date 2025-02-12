package nl.rabobank.org_users_rest.repository

import nl.rabobank.org_users_rest.model.User

interface UsersRepository {
    fun findAll(): MutableList<User>;
    fun addOne(newUser: User): MutableList<User>;
    fun updateOne(index: Int, updatedUser: User): User;
    fun deleteOne(index: Int): String;
    fun save(data: MutableList<User>): String;
    fun findIndexById(id: Int): Int;
}