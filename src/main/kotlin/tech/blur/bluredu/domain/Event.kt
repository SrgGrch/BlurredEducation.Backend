package tech.blur.bluredu.domain

import tech.blur.bluredu.entity.PlaceEntity
import tech.blur.bluredu.entity.UserEntity
import java.sql.Date

data class Event(
        val id: Int,
        val date: Date,
        val place: PlaceEntity,
        val participants: List<UserEntity>,
        val guests: List<UserEntity>
)