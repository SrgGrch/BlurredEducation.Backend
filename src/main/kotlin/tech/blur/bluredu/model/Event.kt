package tech.blur.bluredu.model

import tech.blur.bluredu.repository.entity.PlaceEntity
import java.sql.Date

data class Event(
        val id: Int,
        val name: String,
        val description: String,
        val date: Date,
        val place: PlaceEntity,
        val organizer: Company,
        val eventType: EventType,
        val participants: List<User>,
        val guests: List<User>,
        val sponsors: List<Company>
)