package pl.dkobylarz.signlearning.infrastructure.security

import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import pl.dkobylarz.signlearning.domain.authorization.domain.UserDetailsServiceImpl

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfigKt(
    private val authEntryPoint: AuthEntryPoint,
    private val jwtTokenFilter: JwtTokenFilter,
    private val userDetailsServiceImpl: UserDetailsServiceImpl
) : WebSecurityConfigurerAdapter() {

    companion object {
        private val AUTH_WHITELIST = arrayOf(
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/",
            "/auth/**"
        )
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsServiceImpl)?.passwordEncoder(passwordEncoder())
    }

    override fun configure(http: HttpSecurity?) {
        http?.cors()?.and()?.csrf()?.disable()
            ?.exceptionHandling()?.authenticationEntryPoint(authEntryPoint)?.and()
            ?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)?.and()
            ?.authorizeRequests()?.antMatchers(*AUTH_WHITELIST)?.permitAll()
            ?.anyRequest()?.authenticated()

        http?.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}