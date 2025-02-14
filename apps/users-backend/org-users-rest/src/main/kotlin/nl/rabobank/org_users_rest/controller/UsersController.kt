package nl.rabobank.org_users_rest.controller

import nl.rabobank.org_users_rest.model.UpdateUserDto
import nl.rabobank.org_users_rest.model.User
import nl.rabobank.org_users_rest.model.UserDto
import nl.rabobank.org_users_rest.service.UsersService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping("/api/users")
class UsersController(private val usersService: UsersService) {

    @GetMapping("/", "")
    fun getUsers(): ResponseEntity<List<User>> = ResponseEntity.ok(usersService.getUsers());

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Int): ResponseEntity<User> = ResponseEntity.ok(usersService.getUser(id));

    @PostMapping("/", "")
    fun addUser(@RequestBody userData: UserDto): ResponseEntity<List<User>> =
        ResponseEntity.accepted().body(usersService.addUser(userData))

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Int, @RequestBody userData: UpdateUserDto): ResponseEntity<User> =
        ResponseEntity.accepted().body(usersService.updateUserById(id, userData));

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Int): ResponseEntity<String> =
        ResponseEntity.accepted().body(usersService.deleteUserById(id));
}