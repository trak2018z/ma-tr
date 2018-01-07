package pl.sylwekczmil.ma.backend.infrastructure.security

import org.springframework.context.annotation.Profile
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import pl.sylwekczmil.ma.backend.domain.user.User
import pl.sylwekczmil.ma.backend.domain.user.UserRepository


interface UserAccessor {
    fun getUser(): User
}

@Component
@Profile("dev", "prod")
class UserAccessorImpl(val userRepository: UserRepository) : UserAccessor {
    override fun getUser(): User {
        val authentication = SecurityContextHolder.getContext().authentication
        return userRepository.findByUsername(authentication.name)
    }
}

@Component
@Profile("test")
class UserAccessorTestImpl : UserAccessor {
    override fun getUser(): User {
        return User("test", "\$2y\$10\$nVcdOrNjk59vGycfvafwU.9dGNmCGE1ftEmbJeh3Wghju/P6ddw12")
    }
}