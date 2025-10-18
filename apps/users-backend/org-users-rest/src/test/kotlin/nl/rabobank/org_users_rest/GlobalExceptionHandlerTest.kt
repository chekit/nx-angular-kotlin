package nl.rabobank.org_users_rest

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.assertj.core.api.Assertions.*

@SpringBootTest
class GlobalExceptionHandlerTest {
    @Test
    fun testTest() {
        assertThat(true).isFalse()
    }
}