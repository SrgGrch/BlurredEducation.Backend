package tech.blur.bluredu.api

import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import tech.blur.bluredu.core.BaseResponseEntity
import tech.blur.bluredu.model.Event
import tech.blur.bluredu.service.AccountService
import tech.blur.bluredu.service.EventService

@Api(tags = ["Events"])
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@RestController("EventController")
class EventsController @Autowired constructor(
        val eventService: EventService,
        val accountService: AccountService
) {

    @ResponseBody
    @GetMapping("$EVENTS_ROOT/GetAll")
    fun getAllEvents(): BaseResponseEntity<List<Event>> = BaseResponseEntity(eventService.getAllEvents())


    @ResponseBody
    @GetMapping("$EVENTS_ROOT/{id}")
    fun getEvent(
            @PathVariable id: Int
    ): BaseResponseEntity<Event> {
        return eventService.getEvent(id)?.let {
            BaseResponseEntity(it)
        } ?: BaseResponseEntity<Event>(null, HttpStatus.NOT_FOUND)
    }


    @ResponseBody
    @GetMapping("${EVENTS_ROOT}/Create")
    fun createEvent(
            @RequestHeader("Authentication") token: String
    ): BaseResponseEntity<String> {
        if (accountService.isTokenValid(token)) return BaseResponseEntity(null, HttpStatus.UNAUTHORIZED)
        return BaseResponseEntity("Привет")
    }

    companion object {
        const val EVENTS_ROOT = "Events"
    }
}