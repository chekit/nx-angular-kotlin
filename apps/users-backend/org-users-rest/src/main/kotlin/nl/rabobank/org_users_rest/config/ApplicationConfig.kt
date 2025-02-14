package nl.rabobank.org_users_rest.config

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import nl.rabobank.org_users_rest.repository.DbUsersRepository
import nl.rabobank.org_users_rest.repository.FsUsersRepository
import nl.rabobank.org_users_rest.repository.UsersRepository
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfig {
    @Bean
    @ConditionalOnProperty(
        prefix = "app", name = ["org-users.source"], havingValue = "local"
    )
    fun defineFsRepository(): UsersRepository {
        val mapper = JsonMapper.builder()
            .addModule(KotlinModule.Builder().build())
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true).build();

        return FsUsersRepository(mapper);
    }

    @Bean
    @ConditionalOnProperty(
        prefix = "app", name = ["org-users.source"], havingValue = "db"
    )
    fun defineDbRepository(): UsersRepository {
        return DbUsersRepository();
    }
}