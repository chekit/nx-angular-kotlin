package nl.rabobank.org_users_rest.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
@Access(AccessType.FIELD)
data class User(
    @Id val id: Int = -1,
    @Column(name="first_name") val firstName: String = "",
    @Column(name="last_name") val lastName: String = "",
    @Column(name="role") val role: String = ""
) {}
