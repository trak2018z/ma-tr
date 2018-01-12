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
        val user = userRepository.save(User("username", "\$2a\$10\$7DlN9HrS.mNy3fOqizR.Tucw3ryh/IxbDKAbPoDaGbEsk1zLBP1JW"))

        gridFsTemplate.delete(null)
        dashboardRepository.deleteAll()
        var d = Dashboard()
        d.name = "Mój dashboard"
        d.greetingMessage = "Witaj ${user.username}!"
        d.username = user.username
        d.notes = mutableListOf(Note("Zakupy", "kupić mleko, platki"))
        d.contacts = mutableListOf(Contact("Jan", "Kowalski", "123-123-123", "Przyjaciel",
                Address("Rzeszow", "Wincentego Pola", "1")))
        d.username = "username"
        dashboardRepository.save(d)
    }
}