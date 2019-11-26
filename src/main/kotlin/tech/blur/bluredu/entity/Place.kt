package tech.blur.bluredu.entity

import javax.persistence.Id

data class Place(
        @Id
        val id: Int,
        val address: String
)
