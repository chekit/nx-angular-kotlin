package nl.rabobank.org_users_rest

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import nl.rabobank.org_users_rest.controller.RolesController
import nl.rabobank.org_users_rest.entity.Role
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container

/**
 * The test will run by starting the entire application on a random available port.
 *
 * @see {@link https://testcontainers.com/guides/testing-spring-boot-rest-api-using-testcontainers/#_write_test_for_api_endpoint}
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RolesControllerTest() {
    @LocalServerPort
    private val port: Int? = null

    companion object  {
        @Container
        @ServiceConnection
        var postgres: PostgreSQLContainer<*> = PostgreSQLContainer(
            "postgres:16-alpine"
        )
    }

    @BeforeAll
    fun beforeAll() {
        postgres.start()
    }

    @AfterAll
    fun afterAll() {
        postgres.stop()
    };


    @Autowired
    private lateinit var underTest: RolesController

    @BeforeEach
    fun setUp() {
        RestAssured.baseURI = "http://localhost:${port}";
    }

    @Test
    fun `when request roles the list of all roles returned`() {
        val expectedRolesList: List<Role> = listOf(Role(0, "User"), Role(1, "Moderator"), Role(2, "Admin"))

        given()
            .contentType(ContentType.JSON)
            .`when`()
            .get("/api/roles")
            .then()
            .statusCode(200)
            .body(".", hasSize<Int>(3))
            .equals(expectedRolesList)
    }

    @Test
    fun `when request role by ID the role returned`() {
        val expectedRole: Role = Role(1, "Moderator")

        given()
            .contentType(ContentType.JSON)
            .`when`()
            .get("/api/roles/1")
            .then()
            .statusCode(200)
            .equals(expectedRole)
    }
}