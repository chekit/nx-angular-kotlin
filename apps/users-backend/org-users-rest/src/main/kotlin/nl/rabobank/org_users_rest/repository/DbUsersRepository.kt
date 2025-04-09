package nl.rabobank.org_users_rest.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import nl.rabobank.org_users_rest.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.Optional


interface DbUsersRepository: CrudRepository<User, Int>;