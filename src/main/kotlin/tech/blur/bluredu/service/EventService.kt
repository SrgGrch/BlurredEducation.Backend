package tech.blur.bluredu.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tech.blur.bluredu.domain.Event
import tech.blur.bluredu.domain.EventType
import tech.blur.bluredu.entity.toCompany
import tech.blur.bluredu.repository.EventRepository

@Service("EventService")
class EventService @Autowired constructor(
        private val eventRepository: EventRepository
) {
    val getAllEvents: (() -> List<Event>) =
            {
                eventRepository.findAll().map {
                    Event(
                            id = it.id,
                            date = it.date,
                            name = it.name,
                            description = it.description,
                            organizer = it.organizer.toCompany(),
                            event_type = EventType.resolveEventTypeByName(it.eventType.typeName),
                            guests = it.guests,
                            participants = it.participants,
                            place = it.place,
                            sponsors = it.sponsors.map { sponsor -> sponsor.toCompany() }
                    )
                }
            }
}