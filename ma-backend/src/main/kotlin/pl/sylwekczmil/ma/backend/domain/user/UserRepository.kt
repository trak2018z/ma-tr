package pl.sylwekczmil.ma.backend.domain.user

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(exported = false)
interface UserRepository : MongoRepository<User, String> {
    fun findByUsername(username: String): User
}
