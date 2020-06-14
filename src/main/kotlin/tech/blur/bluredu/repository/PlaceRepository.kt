package tech.blur.bluredu.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tech.blur.bluredu.repository.entity.PlaceEntity

@Repository("PlaceRepository")
interface PlaceRepository : JpaRepository<PlaceEntity, Int>