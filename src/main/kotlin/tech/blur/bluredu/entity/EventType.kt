package tech.blur.bluredu.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "event_types")
data class EventType(
        @Id
        val id: Int,

        @Column(name = "type_name")
        val typeName: String
) : Serializable