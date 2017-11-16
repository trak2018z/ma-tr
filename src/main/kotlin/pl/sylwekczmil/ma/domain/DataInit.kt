package pl.sylwekczmil.ma.domain

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import pl.sylwekczmil.ma.domain.jpa.user.User
import pl.sylwekczmil.ma.domain.jpa.user.UserRepository
import pl.sylwekczmil.ma.domain.mongo.dashboard.Dashboard
import pl.sylwekczmil.ma.domain.mongo.dashboard.DashboardRepository
import pl.sylwekczmil.ma.domain.mongo.dashboard.contact.Address
import pl.sylwekczmil.ma.domain.mongo.dashboard.contact.Contact
import pl.sylwekczmil.ma.domain.mongo.dashboard.note.Note
import pl.sylwekczmil.ma.domain.mongo.dashboard.task.Task

@Component
class DataInit {
    @Bean
    fun init(userRepository: UserRepository, dashboardRepository: DashboardRepository) = CommandLineRunner {

        userRepository.deleteAll()
        val user = userRepository.save(User("user"))


        dashboardRepository.deleteAll()
        var d = Dashboard()
        d.greetingMessage = "Hello" + user.username
        d.username = user.username
        d.tasks = listOf(Task("task1", "task1 desc"))
        d.notes = listOf(Note("note1", "note1 desc"))
        //firstName: String, lastName: String, phoneNumber: String, description: String, username: String?)
        d.contacts = listOf(Contact("Jan", "Kowalski", "123-123-123", "Friend",
                Address("Rzeszow", "Wincentego Pola", "1")))
    }
}