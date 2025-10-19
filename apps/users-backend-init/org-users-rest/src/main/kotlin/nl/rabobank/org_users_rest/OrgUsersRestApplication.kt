package nl.rabobank.org_users_rest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableJpaRepositories
class OrgUsersRestApplication

fun main(args: Array<String>) {
	runApplication<OrgUsersRestApplication>(*args)
}
