package nl.rabobank.usersbackend.repository

import nl.rabobank.usersbackend.entity.Role
import org.springframework.data.repository.CrudRepository

interface DbRoleRepository: CrudRepository<Role, Int>;