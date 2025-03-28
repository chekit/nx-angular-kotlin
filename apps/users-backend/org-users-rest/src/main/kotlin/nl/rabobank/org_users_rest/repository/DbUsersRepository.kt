package nl.rabobank.org_users_rest.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import nl.rabobank.org_users_rest.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.Optional

class DbUsersRepository(
    @PersistenceContext private val entityManager: EntityManager
) : CrudRepository<User, Int>, UsersRepository {
    override fun <S : User?> save(entity: S & Any): S & Any {
        TODO("Not yet implemented")
    }

    override fun <S : User?> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): Optional<User> {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableList<User> {
        val query = entityManager.createQuery("SELECT u from User u", User::class.java);

        return query.resultList.toMutableList()
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<User>) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<Int>) {
        TODO("Not yet implemented")
    }

    override fun delete(entity: User) {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<Int>): MutableIterable<User> {
        TODO("Not yet implemented")
    }

    override fun findUserById(id: Int): User? {
        TODO("Not yet implemented")
    }

    override fun addOne(newUser: User): MutableList<User> {
        // Doesn't work
        // val result = entityManager.createNativeQuery("insert into users (first_name, last_name, role) values ('Antonio', 'Cherepov', 'Admin')")
        println(newUser)

        return findAll()
    }

    override fun updateOne(updatedUser: User): User {
        TODO("Not yet implemented")
    }

    override fun deleteOne(id: Int): String? {
        TODO("Not yet implemented")
    }

    override fun save(data: List<User>): String {
        TODO("Not yet implemented")
    }
}