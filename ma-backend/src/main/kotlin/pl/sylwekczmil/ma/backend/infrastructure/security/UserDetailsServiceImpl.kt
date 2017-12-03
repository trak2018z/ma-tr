package pl.sylwekczmil.ma.backend.infrastructure.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import pl.sylwekczmil.jwt.domain.user.JwtUser

@Service
class UserDetailsServiceImpl : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return JwtUser("test", "test", listOf())
    }
}