package tech.blur.bluredu.domain

import tech.blur.bluredu.entity.PlaceEntity
import tech.blur.bluredu.entity.UserEntity
import java.sql.Date

data class Event(
        val id: Int,
        val name: String,
        val description: String,
        val date: Date,
        val place: PlaceEntity,
        val organizer: Company,
        val event_type: EventType,
        val participants: List<UserEntity>,
        val guests: List<UserEntity>,
        val sponsors: List<Company>
)