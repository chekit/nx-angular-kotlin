package nl.rabobank.org_users_rest

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import nl.rabobank.org_users_rest.controller.UsersController
import nl.rabobank.org_users_rest.model.AddUserDto
import nl.rabobank.org_users_rest.model.UpdateUserDto
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.*
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
class UsersControllerTest() {
    @LocalServerPort
    private val port: Int? = null

    companion object {
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
    private lateinit var underTest: UsersController

    @BeforeEach
    fun setUp() {
        RestAssured.baseURI = "http://localhost:${port}";
    }

    @Test
    fun `when request users from empty DB return empty list`() {
        given()
            .`when`()
            .get("/api/users")
            .then()
            .statusCode(200)
            .body(".", hasSize<Int>(greaterThanOrEqualTo(0)))
    }

    @Test
    fun `when add user return the updated list of users`() {
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

    @Test
    fun `when request user by id return exact user`() {
        val testUser = AddUserDto("Test First Name", "Test Second Name", 2)
        val addedUser = underTest.addUser(testUser).body.let { it?.getOrNull(it.size.dec()) };

        given()
            .with()
            .`when`()
            .get("/api/users/${addedUser?.id}")
            .then()
            .statusCode(200)
            .body(
                "id", equalTo(addedUser?.id),
                "firstName", equalTo(testUser.firstName),
                "lastName", equalTo(testUser.lastName),
                "role", equalTo(addedUser?.role)
            )
    }

    @Test
    fun `when request non-existent user by id fails with 404`() {
        val testId = 42;

        given()
            .with()
            .`when`()
            .get("/api/users/$testId")
            .then()
            .statusCode(404)
            .body("message", equalTo("User with ID $testId not found"))
    }

    @Test
    fun `when request user update return updated user`() {
        val testUser = AddUserDto("Test First Name", "Test Second Name", 2)
        val addedUser = underTest.addUser(testUser).body.let { it?.getOrNull(it.size.dec()) };

        val updateUser = UpdateUserDto(firstName = "Updated First Name", lastName = null, role = 0)

        given()
            .with()
            .body(updateUser)
            .contentType(ContentType.JSON)
            .`when`()
            .put("/api/users/${addedUser?.id}")
            .then()
            .statusCode(202)
            .body(
                "firstName", equalTo(updateUser.firstName),
                "role", equalTo("User")
            )
    }

    @Test
    fun `when request non-existent user update fails with 404`() {
        val updateUser = UpdateUserDto(firstName = "Updated First Name", lastName = null, role = 0)
        val testId = 42;

        given()
            .with()
            .body(updateUser)
            .contentType(ContentType.JSON)
            .`when`()
            .get("/api/users/$testId")
            .then()
            .statusCode(404)
            .body("message", equalTo("User with ID $testId not found"))
    }

    @Test
    fun `when request user delete return OK`() {
        val testUser = AddUserDto("Test First Name", "Test Second Name", 2)
        val addedUser = underTest.addUser(testUser).body.let { it?.getOrNull(it.size.dec()) };

        given()
            .`when`()
            .delete("/api/users/${addedUser?.id}")
            .then()
            .statusCode(202)
            .body(containsString("OK"))
    }

    @Test
    fun `when request non-existent user delete fails with 404`() {
        val testId = 42;

        given()
            .`when`()
            .delete("/api/users/$testId")
            .then()
            .statusCode(404)
            .body("message", equalTo("User with ID $testId not found"))
    }
}