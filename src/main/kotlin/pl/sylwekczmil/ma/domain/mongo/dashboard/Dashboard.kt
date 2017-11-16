package pl.sylwekczmil.ma.domain.mongo.dashboard

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.mongodb.core.mapping.Document
import pl.sylwekczmil.ma.domain.mongo.BaseDocument
import pl.sylwekczmil.ma.domain.mongo.dashboard.contact.Contact
import pl.sylwekczmil.ma.domain.mongo.dashboard.note.Note
import pl.sylwekczmil.ma.domain.mongo.dashboard.task.Task

@Document
class Dashboard : BaseDocument() {

    var name = ""
    var greetingMessage = ""
    var tasks = listOf<Task>()
    var notes = listOf<Note>()
    var contacts = listOf<Contact>()

    @JsonIgnore
    var username: String? = null
}