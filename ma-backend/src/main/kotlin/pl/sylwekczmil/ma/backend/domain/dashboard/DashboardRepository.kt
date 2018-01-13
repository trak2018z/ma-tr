package pl.sylwekczmil.ma.backend.domain.dashboard

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.query.Param

interface DashboardRepository : MongoRepository<Dashboard, String> {
    fun findByUsername(@Param("username") username: String): Dashboard
}
