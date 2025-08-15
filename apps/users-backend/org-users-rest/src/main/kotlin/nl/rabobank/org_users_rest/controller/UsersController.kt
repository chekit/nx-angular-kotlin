package nl.rabobank.org_users_rest.controller

import jakarta.validation.Valid
import nl.rabobank.org_users_rest.model.AddUserDto
import nl.rabobank.org_users_rest.model.UpdateUserDto
import nl.rabobank.org_users_rest.model.UserDto
import nl.rabobank.org_users_rest.service.UsersService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UsersController(private val usersService: UsersService) {

    @GetMapping("/", "")
    fun getUsers(): ResponseEntity<List<UserDto>> = usersService.getUsers().let { ResponseEntity.ok(it) };

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Int): ResponseEntity<UserDto> =
        usersService.getUserById(id).let { ResponseEntity.ok(it) }

    @PostMapping("/", "")
    fun addUser(@RequestBody @Valid userData: AddUserDto): ResponseEntity<List<UserDto>> =
        usersService.addUser(userData).let { ResponseEntity.accepted().body(it) }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Int, @RequestBody userData: UpdateUserDto): ResponseEntity<UserDto> =
        usersService.updateUserById(id, userData).let { ResponseEntity.accepted().body(it) }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Int): ResponseEntity<String> =
        usersService.deleteUserById(id).let { ResponseEntity.accepted().body(it) }
}