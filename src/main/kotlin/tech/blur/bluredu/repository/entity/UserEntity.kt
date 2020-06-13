package tech.blur.bluredu.repository.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(
        @Id
        val id: Int,
        val name: String,
        val nickname: String,
        val email: String,
        val token: String?,
        @Column(name = "password_hash")
        val passwordHash: String,

        @Column(name = "notification_token")
        val notificationToken: String?,

        @ManyToOne
        @JoinColumn(name = "company", referencedColumnName = "id")
        val company: CompanyEntity?
) : Serializable