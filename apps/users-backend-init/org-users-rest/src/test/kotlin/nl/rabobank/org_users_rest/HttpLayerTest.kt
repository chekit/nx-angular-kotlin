package nl.rabobank.org_users_rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions.assertThat

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HttpLayerTest {
    @LocalServerPort
    private var port = 0;

    @Autowired
    private var restTemplate: TestRestTemplate? = null;

    @Test
    @Throws(Exception::class)
    fun greetingShouldReturnDefaultMessage() {
        assertThat(
            this.restTemplate!!.getForObject(
                "http://localhost:$port/api/users",
                List::class.java
            )
        ).isEqualTo(emptyList<Any>())
    }

    @Test
    @Throws(Exception::class)
    fun greetingShouldErrorDefaultMessage() {
        val response = this.restTemplate!!.getForEntity(
            "http://localhost:$port/api/userz",
            String::class.java
        );

        assertThat(response.statusCode.value()).isEqualTo(404)
    }
}