package pl.sylwekczmil.ma.backend.infrastructure.security

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import pl.sylwekczmil.jwt.domain.user.JwtUser
import pl.sylwekczmil.ma.backend.domain.user.UserRepository

@Service
class UserDetailsServiceImpl(val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByUsername(username!!)
        return JwtUser(user.username, user.password, user.roles.map { r -> SimpleGrantedAuthority(r) })
    }
}