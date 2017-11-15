package pl.sylwekczmil.ma.domain.mongo.dashboard

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.mongodb.core.mapping.Document
import pl.sylwekczmil.ma.domain.mongo.BaseDocument

@Document
class Dashboard : BaseDocument() {

    var name: String = ""

    @JsonIgnore
    var username: String? = null
}