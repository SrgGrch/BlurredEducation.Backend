package tech.blur.bluredu.entity

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "companies")
data class CompanyEntity(
        @Id
        val id: Int,
        val name: String,
        val description: String
) : Serializable