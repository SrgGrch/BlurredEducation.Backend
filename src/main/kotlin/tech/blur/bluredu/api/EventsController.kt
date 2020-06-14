package tech.blur.bluredu.api

import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import tech.blur.bluredu.common.Result
import tech.blur.bluredu.core.BaseResponseEntity
import tech.blur.bluredu.errors.NoSuchUserError
import tech.blur.bluredu.errors.UserAlreadyInEvent
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

    @PutMapping("$EVENTS_ROOT/{id}")
    fun updateEvent(
            @RequestBody event: Event,
            @PathVariable id: String
    ) {
        eventService.updateEventPlace(id.toInt(), event)
    }

    @GetMapping("$EVENTS_ROOT/{id}/UpdatePlace")
    fun updateEventPlace(
            @RequestParam placeId: String,
            @PathVariable id: String
    ) {
        eventService.updateEventPlace(id.toInt(), placeId.toInt())
    }


    @ResponseBody
    @GetMapping("${EVENTS_ROOT}/Create")
    fun createEvent(
            @RequestHeader("Authentication") token: String
    ): BaseResponseEntity<String> {
        if (!accountService.isTokenValid(token)) return BaseResponseEntity(null, HttpStatus.UNAUTHORIZED)
        throw NotImplementedError()
    }

    @ResponseBody
    @GetMapping("$EVENTS_ROOT/{id}/Reg")
    fun registerOnEvent(
            @RequestHeader("Authentication") token: String,
            @PathVariable id: String
    ): BaseResponseEntity<Unit> {
        if (!accountService.isTokenValid(token)) {
            return BaseResponseEntity(null, HttpStatus.UNAUTHORIZED)
        }

        return when (val res = eventService.registerOnEvent(token, id.toInt())) {
            is Result.Success -> {
                BaseResponseEntity(Unit, HttpStatus.NO_CONTENT)
            }
            is Result.Failure -> {
                when (res.error) {
                    is NoSuchElementException -> BaseResponseEntity<Unit>(null, HttpStatus.NOT_FOUND)
                    is NoSuchUserError -> BaseResponseEntity<Unit>(null, HttpStatus.UNAUTHORIZED)
                    is UserAlreadyInEvent -> BaseResponseEntity<Unit>(null, HttpStatus.GONE)
                    else -> BaseResponseEntity<Unit>(null, HttpStatus.I_AM_A_TEAPOT)
                }

            }
        }
    }

    @ResponseBody
    @GetMapping("$EVENTS_ROOT/{id}/IsReg")
    fun isRegisteredOnEvent(
            @RequestHeader("Authentication") token: String,
            @PathVariable id: String
    ): BaseResponseEntity<Boolean> {
        if (!accountService.isTokenValid(token))
            return BaseResponseEntity(false)

        return BaseResponseEntity(eventService.isRegisteredOnEvent(token, id.toInt()))
    }

    companion object {
        const val EVENTS_ROOT = "Events"
    }
}