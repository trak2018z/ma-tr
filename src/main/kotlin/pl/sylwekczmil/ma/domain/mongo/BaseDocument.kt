package pl.sylwekczmil.ma.domain.mongo

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import java.time.LocalDateTime


open class BaseDocument {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    val id: String? = null

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Version
    var version: Long? = null

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreatedDate
    var createdDate: LocalDateTime? = null

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @LastModifiedDate
    var lastModifiedDate: LocalDateTime? = null
}

