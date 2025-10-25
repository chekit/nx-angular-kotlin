package nl.rabobank.usersbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = arrayOf("nl.rabobank"))
@ConfigurationPropertiesScan
@EnableJpaRepositories
class UsersBackendApplication

fun main(args: Array<String>) {
  runApplication<UsersBackendApplication>(*args)
}