package pl.sylwekczmil.ma.backend.domain.mongo.dashboard.file

import java.time.LocalDateTime
import java.util.*

class FileMetadata(var fileName: String, var contentType: String) {
    var id = UUID.randomUUID().toString()
    var type = FileType.OTHER
    var thumbNail = ""
    var uploadDate = LocalDateTime.now()
}

enum class FileType {
    IMAGE, OTHER
}