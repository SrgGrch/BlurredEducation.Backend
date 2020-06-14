package tech.blur.bluredu.model

enum class EventType {
    Workshop,
    Lecture,
    Networking,
    Meeting,
    Lifestyle,
    Cinema;

    companion object {
        fun resolveEventTypeByName(eventType: String): EventType {
            return when (eventType) {
                "Воркшоп" -> Workshop
                "Доклад" -> Lecture
                "Кино" -> Cinema
                "Лайфстайл" -> Lifestyle
                "Митинг" -> Meeting
                "Нетворкинг" -> Networking
                else -> throw IllegalArgumentException("EventType must be one of DB event_type!")
            }
        }
    }

}