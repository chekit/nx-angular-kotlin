package nl.rabobank.usersbackend

import nl.rabobank.usersbackend.models.UserData
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @CrossOrigin(origins = ["http://localhost:4200"])
    @GetMapping("/")
    fun greeting(): ResponseEntity<UserData> = ResponseEntity.ok(UserData("Test", "Admin"))
}
