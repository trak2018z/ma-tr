package pl.sylwekczmil.ma.domain

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import pl.sylwekczmil.ma.domain.jpa.user.User
import pl.sylwekczmil.ma.domain.jpa.user.UserRepository
import pl.sylwekczmil.ma.domain.mongo.dashboard.DashboardRepository

@Component
class DataInit {
    @Bean
    fun init(userRepository: UserRepository, dashboardRepository: DashboardRepository) = CommandLineRunner {

        userRepository.deleteAll()
        val u1 = userRepository.save(User("Name1"))
        println(u1.id)
        dashboardRepository.deleteAll()
    }
}