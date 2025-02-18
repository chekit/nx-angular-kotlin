package nl.rabobank.org_users_rest.repository

import nl.rabobank.org_users_rest.model.User

interface UsersRepository {
    fun findAll(): MutableList<User.Entity>;
    fun findById(id: Int): User.Entity?;
    fun addOne(newUser: User.Entity): MutableList<User.Entity>;
    fun updateOne(updatedUser: User.Entity): User.Entity;
    fun deleteOne(id: Int): String?;
    fun save(data: MutableList<User.Entity>): String;
}