package nl.rabobank.org_users_rest.repository

import nl.rabobank.org_users_rest.model.User

interface UsersRepository {
    fun findAll(): MutableList<User>;
    fun addOne(): MutableList<User>;
    fun updateOne(): User;
    fun deleteOne(): String;
    fun save(data: MutableList<User>): String;
}