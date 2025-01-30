package nl.rabobank.org_users_rest.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/users")
class UsersController {
    @Autowired
    private val template: RedisTemplate<String, String>? = null

    private val STRING_KEY_PREFIX: String = "redi2read:strings:"

    @GetMapping("/", "")
    fun getUsers(): String {
        return "Hello"
    }

    @PostMapping("/", "")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun addUser(@RequestBody userData: String): String {
        template?.opsForValue()?.set("${STRING_KEY_PREFIX}_${userData}", userData);

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