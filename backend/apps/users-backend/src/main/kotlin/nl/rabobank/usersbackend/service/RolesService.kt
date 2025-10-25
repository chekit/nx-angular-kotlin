package nl.rabobank.usersbackend.service

import nl.rabobank.usersbackend.entity.Role
import nl.rabobank.usersbackend.repository.DbRoleRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class RolesService(private val roleRepo: DbRoleRepository) {
    fun getRoles(): List<Role> = roleRepo.findAll()
        .toList()

    fun getRoleById(id: Int): Role? =
        roleRepo.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Can't find user role") }
}