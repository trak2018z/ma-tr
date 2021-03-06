package pl.sylwekczmil.ma.backend.domain.dashboard

import org.springframework.data.mongodb.core.mapping.Document
import pl.sylwekczmil.ma.backend.domain.BaseDocument
import pl.sylwekczmil.ma.backend.domain.dashboard.contact.Contact
import pl.sylwekczmil.ma.backend.domain.dashboard.file.FileMetadata
import pl.sylwekczmil.ma.backend.domain.dashboard.note.Note

@Document
class Dashboard() : BaseDocument() {

    var name = "New dashboard"
    var greetingMessage = ""
    var notes = mutableListOf<Note>()
    var contacts = mutableListOf<Contact>()
    var files = mutableListOf<FileMetadata>()
    var username: String? = null

    constructor(username: String?) : this() {
        this.username = username
    }
}