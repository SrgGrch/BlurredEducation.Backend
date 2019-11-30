package tech.blur.bluredu.entity

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class CompanyEntity(
        @Id
        val id: String,
        val name: String,
        val description: String
) : Serializable