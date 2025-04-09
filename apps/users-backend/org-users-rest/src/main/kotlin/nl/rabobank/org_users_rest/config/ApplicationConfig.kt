package nl.rabobank.org_users_rest.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@ConfigurationProperties(prefix = "app.org-users")
data class AppProperties(val source: String, val url: String)

@Configuration
class ApplicationConfig {
}