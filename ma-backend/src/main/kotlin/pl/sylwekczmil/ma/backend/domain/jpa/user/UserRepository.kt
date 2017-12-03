package pl.sylwekczmil.ma.backend.domain.jpa.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(exported = false)
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): List<User>
}
