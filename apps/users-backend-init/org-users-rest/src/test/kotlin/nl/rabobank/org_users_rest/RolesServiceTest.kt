package nl.rabobank.org_users_rest

import nl.rabobank.org_users_rest.service.RolesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
class RolesServiceTest {
    @Autowired
    lateinit var underTest: RolesService

    @Test
    fun canGetAllRoles() {
        assertEquals(true, false)
    }
}