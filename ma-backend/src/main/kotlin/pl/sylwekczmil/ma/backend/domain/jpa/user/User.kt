package pl.sylwekczmil.ma.backend.domain.jpa.user

import org.hibernate.validator.constraints.Length
import org.jetbrains.annotations.NotNull
import pl.sylwekczmil.ma.backend.domain.jpa.BaseEntity
import javax.persistence.Entity

@Entity
class User(
        @NotNull
        @Length(min = 5, max = 50)
        val username: String
) : BaseEntity() {

    var firstname = ""
    var lastname = ""

}