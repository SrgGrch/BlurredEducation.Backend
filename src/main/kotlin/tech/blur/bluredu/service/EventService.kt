package tech.blur.bluredu.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tech.blur.bluredu.entity.EventEntity
import tech.blur.bluredu.entity.toEvent
import tech.blur.bluredu.model.Event
import tech.blur.bluredu.repository.EventRepository

@Service("EventService")
class EventService @Autowired constructor(
        private val eventRepository: EventRepository
) {
    val getAllEvents: (() -> List<Event>) = {
        eventRepository.findAll().map(EventEntity::toEvent)
    }

    fun getEvent(id: Int): Event? = eventRepository.findById(id).orElseGet { null }.toEvent()
}