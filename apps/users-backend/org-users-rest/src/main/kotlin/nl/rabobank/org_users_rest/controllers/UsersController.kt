package nl.rabobank.org_users_rest.controllers

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UsersController {
    @GetMapping("/", "")
    fun getUsers(): String {
        return "Hello"
    }

    @PostMapping("/", "")
    fun addUser(@RequestBody userData: String): String {
        return "Hello $userData"
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: String): String {
        return "Hello $id"
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: String): String {
        return "Hello $id"
    }
}