package nl.rabobank.usersbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = arrayOf("nl.rabobank"))
class UsersBackendApplication

fun main(args: Array<String>) {
  runApplication<UsersBackendApplication>(*args)
}


