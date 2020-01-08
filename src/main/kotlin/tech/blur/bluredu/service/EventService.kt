package tech.blur.bluredu.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tech.blur.bluredu.domain.Event
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
                            guests = it.guests,
                            participants = it.participants,
                            place = it.place
                    )
                }
            }
}