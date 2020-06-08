package tech.blur.bluredu.api

import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import tech.blur.bluredu.core.BaseResponseEntity
import tech.blur.bluredu.domain.Event
import tech.blur.bluredu.service.EventService

@Api(tags = ["Events"])
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@RestController("EventController")
class EventsController @Autowired constructor(
        val eventService: EventService
) {

    @ResponseBody
    @GetMapping("$EVENTS_ROOT/GetAll")
    fun getAllEvents(): BaseResponseEntity<List<Event>> = BaseResponseEntity(eventService.getAllEvents())


    companion object {
        const val EVENTS_ROOT = "Events"
    }
}