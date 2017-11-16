package pl.sylwekczmil.ma.domain.mongo.dashboard.task

import java.time.LocalDateTime

class Task() {

    var title = ""
    var description = ""
    var dueDate = LocalDateTime.now().plusDays(1)


    constructor(title: String, description: String) : this() {
        this.title = title
        this.description = description
    }
}
