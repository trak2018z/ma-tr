package pl.sylwekczmil.ma.backend.domain

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.gridfs.GridFsTemplate
import org.springframework.stereotype.Component
import pl.sylwekczmil.ma.backend.domain.jpa.user.User
import pl.sylwekczmil.ma.backend.domain.jpa.user.UserRepository
import pl.sylwekczmil.ma.backend.domain.mongo.dashboard.Dashboard
import pl.sylwekczmil.ma.backend.domain.mongo.dashboard.DashboardRepository
import pl.sylwekczmil.ma.backend.domain.mongo.dashboard.contact.Address
import pl.sylwekczmil.ma.backend.domain.mongo.dashboard.contact.Contact
import pl.sylwekczmil.ma.backend.domain.mongo.dashboard.note.Note

@Component
class DataInit {
    @Bean
    fun init(userRepository: UserRepository, dashboardRepository: DashboardRepository,
             gridFsTemplate: GridFsTemplate) = CommandLineRunner {

        userRepository.deleteAll()
        val user = userRepository.save(User("user"))

        gridFsTemplate.delete(null)
        dashboardRepository.deleteAll()
        var d = Dashboard()
        d.id = "a"
        d.name = "My dash"
        d.greetingMessage = "Hello ${user.username}"
        d.username = user.username
        d.notes = mutableListOf(Note("note1", "note1 desc"))
        d.contacts = mutableListOf(Contact("Jan", "Kowalski", "123-123-123", "Friend",
                Address("Rzeszow", "Wincentego Pola", "1")))
        d.username = "user"
        dashboardRepository.save(d)
    }
}