package nl.rabobank.org_users_rest.config

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import nl.rabobank.org_users_rest.repository.FsUsersRepository
import nl.rabobank.org_users_rest.repository.UsersRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class ApplicationConfig {
    @Bean
    fun repository(): UsersRepository {
        val mapper = JsonMapper.builder()
            .addModule(KotlinModule.Builder().build())
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true).build();

        return FsUsersRepository(mapper);
    }

}