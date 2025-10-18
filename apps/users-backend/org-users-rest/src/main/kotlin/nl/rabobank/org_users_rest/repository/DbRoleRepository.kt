package nl.rabobank.org_users_rest.repository

import nl.rabobank.org_users_rest.entity.Role
import org.springframework.data.repository.CrudRepository

interface DbRoleRepository: CrudRepository<Role, Int>;