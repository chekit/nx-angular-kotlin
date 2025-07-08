package nl.rabobank.org_users_rest.service

import nl.rabobank.org_users_rest.repository.DbRoleRepository
import org.springframework.stereotype.Service

@Service
class RolesService(private val roleRepo: DbRoleRepository) {
    fun getRoles() = roleRepo.findAll()
        .toList()
}