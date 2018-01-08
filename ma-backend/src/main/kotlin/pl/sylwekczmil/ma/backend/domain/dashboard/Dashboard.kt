package pl.sylwekczmil.ma.backend.domain.dashboard

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.mongodb.core.mapping.Document
import pl.sylwekczmil.ma.backend.domain.BaseDocument
import pl.sylwekczmil.ma.backend.domain.dashboard.contact.Contact
import pl.sylwekczmil.ma.backend.domain.dashboard.file.FileMetadata
import pl.sylwekczmil.ma.backend.domain.dashboard.note.Note

@Document
class Dashboard() : BaseDocument() {

    var name = ""
    var greetingMessage = ""
    var notes = mutableListOf<Note>()
    var contacts = mutableListOf<Contact>()
    var files = mutableListOf<FileMetadata>()

    @JsonIgnore
    var username: String? = null

    constructor(username: String?) : this() {
        this.username = username
    }
}