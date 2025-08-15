package nl.rabobank.org_users_rest

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import nl.rabobank.org_users_rest.controller.UsersController
import nl.rabobank.org_users_rest.entity.Role
import nl.rabobank.org_users_rest.entity.User
import nl.rabobank.org_users_rest.model.AddUserDto
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container

/**
 * The test will run by starting the entire application on a random available port.
 *
 * @see {@link https://testcontainers.com/guides/testing-spring-boot-rest-api-using-testcontainers/#_write_test_for_api_endpoint}
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersControllerTest() {
    @LocalServerPort
    private val port: Int? = null

    companion object {
        @Container
        var postgres: PostgreSQLContainer<*> = PostgreSQLContainer(
            "postgres:16-alpine"
        )

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            postgres.start()
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            postgres.stop()
        };

        @JvmStatic
        @DynamicPropertySource
        fun configureProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgres::getJdbcUrl);
            registry.add("spring.datasource.username", postgres::getUsername);
            registry.add("spring.datasource.password", postgres::getPassword);
        }
    }

    @Autowired
    private lateinit var underTest: UsersController

    @BeforeEach
    fun setUp() {
        RestAssured.baseURI = "http://localhost:${port}";
    }

    @Test
    fun `when request users from empty DB return empty list`() {
        given()
            .contentType(ContentType.JSON)
            .`when`()
            .get("/api/users")
            .then()
            .statusCode(200)
            .body(".", hasSize<Int>(greaterThanOrEqualTo(0)))
    }

    @Test
    fun `when add user the updated list of users returned`() {
        given()
            .with()
            .body(AddUserDto("Test First Name", "Test Second Name", 2))
            .contentType(ContentType.JSON)
            .`when`()
            .post("/api/users")
            .then()
            .statusCode(202)
            .body(".", hasSize<Int>(greaterThan(0)))
    }

}