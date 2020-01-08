package tech.blur.bluredu.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tech.blur.bluredu.entity.EventEntity

@Repository("EventRepository")
interface EventRepository : JpaRepository<EventEntity, String>