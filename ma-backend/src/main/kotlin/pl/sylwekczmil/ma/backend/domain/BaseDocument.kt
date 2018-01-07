package pl.sylwekczmil.ma.backend.domain

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import java.time.LocalDateTime


open class BaseDocument {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var id: String? = null

    @Version
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var version: Long? = null

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var createdDate: LocalDateTime? = null

    @LastModifiedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var lastModifiedDate: LocalDateTime? = null
}
