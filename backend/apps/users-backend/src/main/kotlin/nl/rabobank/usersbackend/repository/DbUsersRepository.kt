package nl.rabobank.usersbackend.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import nl.rabobank.usersbackend.entity.Role
import nl.rabobank.usersbackend.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.Optional


interface DbUsersRepository : CrudRepository<User, Int>;
