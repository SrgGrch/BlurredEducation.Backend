package tech.blur.bluredu.entity

import java.io.Serializable
import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table
data class EventEntity(
        @Id
        val id: String,
        val date: Timestamp,

        @OneToOne
        @JoinColumn(name = "place", referencedColumnName = "id")
        val place: PlaceEntity,

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "events_participants", joinColumns = [JoinColumn(name = "event_id")], inverseJoinColumns = [JoinColumn(name = "user_id")])
        val participants: List<UserEntity>,

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "events_guests", joinColumns = [JoinColumn(name = "event_id")], inverseJoinColumns = [JoinColumn(name = "guest_id")])
        val guests: List<UserEntity>
) : Serializable