package tech.blur.bluredu.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import tech.blur.bluredu.core.BaseResponseEntity
import tech.blur.bluredu.domain.Event
import tech.blur.bluredu.service.EventService

@Controller("EventController")
class EventController @Autowired constructor(
        val eventService: EventService
) {

    @GetMapping("$EVENTS_ROOT/GetAll")
    fun getAllEvents(): ResponseEntity<List<Event>> = BaseResponseEntity(eventService.getAllEvents())


    companion object {
        const val EVENTS_ROOT = "Events"
    }
}