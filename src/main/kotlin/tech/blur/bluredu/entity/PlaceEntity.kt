package tech.blur.bluredu.entity

import java.io.Serializable
import javax.persistence.Id

data class PlaceEntity(
        @Id
        val id: Int,
        val address: String,
        val description: String
) : Serializable
