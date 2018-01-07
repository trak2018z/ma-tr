package pl.sylwekczmil.ma.backend.domain.user

import org.hibernate.validator.constraints.Length
import org.jetbrains.annotations.NotNull
import org.springframework.data.mongodb.core.mapping.Document
import pl.sylwekczmil.ma.backend.domain.BaseDocument

@Document
class User(
        @NotNull
        @Length(min = 5, max = 50)
        val username: String,
        @NotNull
        val password: String
) : BaseDocument() {

    var firstname = ""
    var lastname = ""
    var roles = mutableListOf<String>()
}