package tech.blur.bluredu.repository.entity

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "places")
data class PlaceEntity(
        @Id
        val id: Int,
        val name: String,
        val description: String
//TODO: Add address
) : Serializable
