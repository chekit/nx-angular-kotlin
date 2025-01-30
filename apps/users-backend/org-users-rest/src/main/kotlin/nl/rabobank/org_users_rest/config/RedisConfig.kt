package nl.rabobank.org_users_rest.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class RedisConfig {

    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<*, *> {
        val template = RedisTemplate<Any, Any>()
        template.connectionFactory = connectionFactory
        return template
    }
}