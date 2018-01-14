package pl.sylwekczmil.ma.backend.domain

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.gridfs.GridFsTemplate
import org.springframework.stereotype.Component
import pl.sylwekczmil.ma.backend.domain.dashboard.Dashboard
import pl.sylwekczmil.ma.backend.domain.dashboard.DashboardRepository
import pl.sylwekczmil.ma.backend.domain.dashboard.contact.Address
import pl.sylwekczmil.ma.backend.domain.dashboard.contact.Contact
import pl.sylwekczmil.ma.backend.domain.dashboard.note.Note
import pl.sylwekczmil.ma.backend.domain.user.User
import pl.sylwekczmil.ma.backend.domain.user.UserRepository

@Component
class DataInit {
    @Bean
    fun init(userRepository: UserRepository, dashboardRepository: DashboardRepository,
             gridFsTemplate: GridFsTemplate) = CommandLineRunner {

        userRepository.deleteAll()
        var user = User("sylwek", "\$2y\$10\$pdGFADm18BgyAKv2E2V4IO3qEpy6YeWffsvt0Nwfs/npJTsfVrWne")
        user.firstname = "Sylwek"
        user = userRepository.save(user)
        gridFsTemplate.delete(null)
        dashboardRepository.deleteAll()
        var d = Dashboard()
        d.name = "Mój dashboard"
        d.greetingMessage = "Witaj ${user.firstname}!"
        d.username = user.username
        d.notes = mutableListOf(Note("Zakupy", "Kupić mleko i platki."))
        d.contacts = mutableListOf(Contact("Jan", "Kowalski", "123-123-123", "Przyjaciel",
                Address("Rzeszow", "Wincentego Pola", "1")))
        d.username = "sylwek"
        dashboardRepository.save(d)
    }
}