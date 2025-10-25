package nl.rabobank.usersbackend.controller

import nl.rabobank.usersbackend.entity.Role
import nl.rabobank.usersbackend.service.RolesService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/roles")
class RolesController(private val rolesService: RolesService) {
    @GetMapping("/", "")
    fun getRoles(): ResponseEntity<List<Role>> = rolesService.getRoles().let { ResponseEntity.ok(it) };

    @GetMapping("/{id}")
    fun getRole(@PathVariable id: Int): ResponseEntity<Role> =
        rolesService.getRoleById(id).let { ResponseEntity.ok(it) };
}