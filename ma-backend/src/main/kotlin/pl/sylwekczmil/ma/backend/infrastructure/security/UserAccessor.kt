package pl.sylwekczmil.ma.backend.infrastructure.security

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import pl.sylwekczmil.ma.backend.domain.jpa.user.User
import pl.sylwekczmil.ma.backend.domain.jpa.user.UserRepository

interface UserAccessor {
    fun getUser(): User
}

@Component
@Profile("dev", "prod")
class UserAccessorImpl(val userRepository: UserRepository) : UserAccessor {
    override fun getUser(): User {
        // TODO: find user by security context
        return userRepository.getOne(1)
    }
}

@Component
@Profile("test")
class UserAccessorTestImpl : UserAccessor {
    override fun getUser(): User {
        return User("test")
    }
}