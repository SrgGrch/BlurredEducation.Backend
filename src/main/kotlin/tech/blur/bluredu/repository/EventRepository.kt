package tech.blur.bluredu.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import tech.blur.bluredu.repository.entity.EventEntity
import tech.blur.bluredu.repository.entity.PlaceEntity
import javax.transaction.Transactional

@Repository("EventRepository")
interface EventRepository : JpaRepository<EventEntity, Int> {
    @Modifying
    @Query("UPDATE EventEntity set name = ?2, description = ?3 where id = ?1")
    @Transactional
    fun updateEvent(id: Int, name: String, description: String)

    @Modifying
    @Query("UPDATE EventEntity set place = ?2 where id = ?1")
    @Transactional
    fun updateEvent(id: Int, name: PlaceEntity)
}