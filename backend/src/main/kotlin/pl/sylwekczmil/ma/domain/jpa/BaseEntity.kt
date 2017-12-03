package pl.sylwekczmil.ma.domain.jpa

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import java.time.LocalDateTime
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class BaseEntity {

    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    val id: Long? = null

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