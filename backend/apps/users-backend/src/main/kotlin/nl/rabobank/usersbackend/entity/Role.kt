package nl.rabobank.usersbackend.entity

import jakarta.persistence.*

@Entity
@Access(AccessType.FIELD)
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    val name: String
) {}