package pl.sylwekczmil.ma.backend.domain.dashboard

import org.springframework.data.mongodb.repository.MongoRepository

interface DashboardRepository : MongoRepository<Dashboard, String> {
    fun findByUsername(username: String): Dashboard
}
