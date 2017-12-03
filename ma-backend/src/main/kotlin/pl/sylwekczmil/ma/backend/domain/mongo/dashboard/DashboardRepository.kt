package pl.sylwekczmil.ma.backend.domain.mongo.dashboard

import org.springframework.data.mongodb.repository.MongoRepository

interface DashboardRepository : MongoRepository<Dashboard, String>
