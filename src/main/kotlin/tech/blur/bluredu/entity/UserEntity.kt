package tech.blur.bluredu.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table
data class UserEntity(
        @Id
        val id: Int,
        val name: String,
        val nickname: String,
        val email: String,

        @Column(name = "password_hash")
        val passwordHash: String,

        @Column(name = "notification_token")
        val notificationToken: String,

        @OneToOne
        @JoinColumn(name = "company", referencedColumnName = "id")
        val company: CompanyEntity?
) : Serializable