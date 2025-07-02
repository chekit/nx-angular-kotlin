package nl.rabobank.org_users_rest.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
@Access(AccessType.FIELD)
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Int = 0,
    @Column(name = "first_name") val firstName: String = "",
    @Column(name = "last_name") val lastName: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    val role: Role
) {}
