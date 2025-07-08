package nl.rabobank.org_users_rest.controller

import nl.rabobank.org_users_rest.entity.Role
import nl.rabobank.org_users_rest.service.RolesService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/roles")
class RolesController(private val rolesService: RolesService) {
    @GetMapping("/", "")
    fun getRoles(): ResponseEntity<List<Role>> = ResponseEntity.ok(rolesService.getRoles());
}