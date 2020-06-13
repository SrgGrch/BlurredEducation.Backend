package tech.blur.bluredu.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tech.blur.bluredu.common.Result
import tech.blur.bluredu.errors.NoSuchUserError
import tech.blur.bluredu.errors.UserAlreadyInEvent
import tech.blur.bluredu.model.Event
import tech.blur.bluredu.repository.EventRepository
import tech.blur.bluredu.repository.entity.EventEntity
import tech.blur.bluredu.repository.entity.toEvent
import java.util.*

@Service("EventService")
class EventService @Autowired constructor(
        private val eventRepository: EventRepository,
        val accountService: AccountService
) {
    val getAllEvents: (() -> List<Event>) = {
        eventRepository.findAll().map(EventEntity::toEvent)
    }

    fun getEvent(id: Int): Event? = eventRepository.findById(id).orElseGet { null }.toEvent()

    fun isRegisteredOnEvent(token: String, id: Int): Boolean {
        val event = eventRepository.findById(id)
        val user = accountService.getUserEntityByToken(token)
        return event.get().guests.contains((user as Result.Success).value) || event.get().participants.contains(user.value)
    }

    fun registerOnEvent(token: String, id: Int): Result<Unit, Exception> {
        val eventOptional = eventRepository.findById(id)
        val user = accountService.getUserEntityByToken(token)
        return when (user) {
            is Result.Success -> {
                if (eventOptional.isPresent) {
                    val event = eventOptional.get()
                    return if (event.guests.contains(user.value) || event.participants.contains(user.value)) {
                        Result.failure<Unit, Exception>(UserAlreadyInEvent())
                    } else {
                        val newEvent = EventEntity(
                                event.id,
                                event.date,
                                event.name,
                                event.description,
                                event.organizer,
                                event.eventType,
                                event.place,
                                event.participants,
                                event.guests.plus(user.value),
                                event.sponsors
                        )
                        eventRepository.save(newEvent)
                        Result.success<Unit, Exception>(Unit)
                    }
                } else {
                    Result.failure<Unit, Exception>(NoSuchElementException())
                }
            }
            is Result.Failure -> {
                Result.failure<Unit, Exception>(NoSuchUserError())
            }
        }
    }
}