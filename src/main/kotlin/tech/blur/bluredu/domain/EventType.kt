package tech.blur.bluredu.domain

enum class EventType {
    WORKSHOP,
    LECTURE,
    NETWORKING,
    MEETING,
    LIFESTYLE,
    CINEMA;

    companion object {
        fun resolveEventTypeByName(eventType: String): EventType {
            return when (eventType) {
                "Воркшоп" -> WORKSHOP
                "Доклад" -> LECTURE
                "Кино" -> NETWORKING
                "Лайфстайл" -> MEETING
                "Митинг" -> LIFESTYLE
                "Нетворкинг" -> CINEMA
                else -> throw IllegalArgumentException("EventType must be one of DB event_type!")
            }
        }
    }

}