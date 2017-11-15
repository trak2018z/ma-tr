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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue
    val id: Long? = null

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