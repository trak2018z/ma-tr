package pl.sylwekczmil.ma.domain.mongo.dashboard.note

class Note() {
    var title = ""
    var description = ""

    constructor(title: String, description: String) : this() {
        this.title = title
        this.description = description
    }
}