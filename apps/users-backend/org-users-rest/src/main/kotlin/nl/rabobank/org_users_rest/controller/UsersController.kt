package nl.rabobank.org_users_rest.controller

import nl.rabobank.org_users_rest.model.UpdateUserDto
import nl.rabobank.org_users_rest.model.User
import nl.rabobank.org_users_rest.model.UserDto
import nl.rabobank.org_users_rest.service.UsersService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/users")
class UsersController(private val usersService: UsersService) {

    @GetMapping("/", "")
    fun getUsers(): MutableList<User> {
        return usersService.getUsers();
    }

    @PostMapping("/", "")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun addUser(@RequestBody userData: UserDto): MutableList<User> {
        return usersService.addUser(userData)
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Int, @RequestBody userData: UpdateUserDto): User {
        return usersService.updateUserById(id, userData);
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Int): String {
        return usersService.deleteUserById(id)
    }
}