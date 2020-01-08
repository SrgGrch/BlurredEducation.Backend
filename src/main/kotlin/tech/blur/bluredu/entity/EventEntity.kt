package tech.blur.bluredu.entity

import java.io.Serializable
import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "events")
data class EventEntity(
        @Id
        val id: Int,

        val date: Date,

        val name: String,

        val description: String,

        @ManyToOne
        @JoinColumn(name = "event_type", referencedColumnName = "id")
        val eventType: EventType,

        @ManyToOne
        @JoinColumn(name = "place", referencedColumnName = "id")
        val place: PlaceEntity,

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "events_participants", joinColumns = [JoinColumn(name = "event_id")], inverseJoinColumns = [JoinColumn(name = "user_id")])
        val participants: List<UserEntity>,

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "events_guests", joinColumns = [JoinColumn(name = "event_id")], inverseJoinColumns = [JoinColumn(name = "guest_id")])
        val guests: List<UserEntity>,

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "event_sponsor", joinColumns = [JoinColumn(name = "event_id")], inverseJoinColumns = [JoinColumn(name = "sponsor_id")])
        val sponsors: List<UserEntity>
) : Serializable