package tech.blur.bluredu.entity

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class User(
        @Id
        val id: Int,
        val name: String,
        val nickName: String,
        val email: String,
        val company: Company?
) : Serializable