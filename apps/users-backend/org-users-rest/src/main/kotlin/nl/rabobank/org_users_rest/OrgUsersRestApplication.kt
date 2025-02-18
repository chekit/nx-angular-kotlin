package nl.rabobank.org_users_rest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class OrgUsersRestApplication

fun main(args: Array<String>) {
	runApplication<OrgUsersRestApplication>(*args)
}
