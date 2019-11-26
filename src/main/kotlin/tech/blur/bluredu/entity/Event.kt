package tech.blur.bluredu.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class Event(
        @Id
        val id: String,
        val place: Place,
        val participants: List<Participant>,
        val guests: List<User>
)