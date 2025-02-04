package nl.rabobank.org_users_rest.controller

import nl.rabobank.org_users_rest.service.UsersService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/users")
class UsersController(private val usersService: UsersService) {

    @GetMapping("/", "")
    fun getUsers(): String {
        return usersService.getUsers() ?: ""
    }

    @PostMapping("/", "")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun addUser(@RequestBody userData: String): String {
        return usersService.addUser(userData) ?: ""
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: String): String {
        return usersService.updateUser(id) ?: "";
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: String): String {
        return  usersService.deleteUser(id) ?: "";
    }
}