package pl.sylwekczmil.ma.domain.mongo.dashboard

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.mongodb.core.mapping.Document
import pl.sylwekczmil.ma.domain.mongo.BaseDocument
import pl.sylwekczmil.ma.domain.mongo.dashboard.contact.Contact
import pl.sylwekczmil.ma.domain.mongo.dashboard.file.FileMetadata
import pl.sylwekczmil.ma.domain.mongo.dashboard.note.Note

@Document
class Dashboard : BaseDocument() {

    var name = ""
    var greetingMessage = ""
    var notes = mutableListOf<Note>()
    var contacts = mutableListOf<Contact>()
    var files = mutableListOf<FileMetadata>()

    @JsonIgnore
    var username: String? = null
}