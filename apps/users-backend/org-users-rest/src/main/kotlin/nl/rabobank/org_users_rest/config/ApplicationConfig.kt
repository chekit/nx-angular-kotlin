package nl.rabobank.org_users_rest.config

import nl.rabobank.org_users_rest.repository.FsUsersRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class ApplicationConfig {
    @Bean fun repository(): FsUsersRepository {
        return FsUsersRepository();
    }
}