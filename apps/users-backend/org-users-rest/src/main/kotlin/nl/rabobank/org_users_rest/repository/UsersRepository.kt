package nl.rabobank.org_users_rest.repository

import nl.rabobank.org_users_rest.model.User

interface UsersRepository {
    fun getAll(): MutableList<User>;
    fun addOne(): MutableList<User>;
    fun updateOne(): User;
    fun deleteOne(): String;
}