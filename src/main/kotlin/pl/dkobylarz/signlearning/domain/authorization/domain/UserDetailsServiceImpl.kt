package pl.dkobylarz.signlearning.domain.authorization.domain

import lombok.RequiredArgsConstructor
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.user.UserFacade

@Service
@RequiredArgsConstructor
class UserDetailsServiceImpl(private val userFacade: UserFacade) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        return userFacade.getUserByUsername(username!!)
    }
}