package nl.rabobank.org_users_rest.controller

import jakarta.validation.Valid
import nl.rabobank.org_users_rest.model.AddUserDto
import nl.rabobank.org_users_rest.model.UserDto
import nl.rabobank.org_users_rest.model.UpdateUserDto
import nl.rabobank.org_users_rest.service.UsersService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/users")
class UsersController(private val usersService: UsersService) {

    @GetMapping("/", "")
    fun getUsers(): ResponseEntity<List<UserDto>> = ResponseEntity.ok(usersService.getUsers());

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Int): ResponseEntity<UserDto> = ResponseEntity.ok(usersService.getUser(id));

    @PostMapping("/", "")
    fun addUser(@RequestBody @Valid userData: AddUserDto): ResponseEntity<List<UserDto>> =
        ResponseEntity.accepted().body(usersService.addUser(userData))

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Int, @RequestBody userData: UpdateUserDto): ResponseEntity<UserDto> =
        ResponseEntity.accepted().body(usersService.updateUserById(id, userData));

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Int): ResponseEntity<String> =
        ResponseEntity.accepted().body(usersService.deleteUserById(id));
}