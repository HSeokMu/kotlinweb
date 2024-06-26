package sm.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import sm.domain.account.service.AccountService

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val accountService: AccountService,
    // private val passwordEncoder: PasswordEncoder
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http.csrf { it.disable() }
            .headers { it.frameOptions { frameOptions -> frameOptions.sameOrigin() } }
            .authorizeHttpRequests {
                    it.requestMatchers("/", "/home", "/login", "/logout", "/account/register").permitAll() // 인증 필요 없음
                      // .requestMatchers("/user/**").hasRole("USER")
                      // .requestMatchers("/admin/**").hasRole("ADMIN")
                      .anyRequest().authenticated() // 나머지 모든 경로는 인증(로그인 성공) 필요
             }
            .formLogin {
                it.loginPage("/login")              // 사용자가 커스텀한 로그인 페이지
                  // .loginProcessingUrl("/loginProc") // 로그인 인증 처리 수행하는 필터 호출
                  // .successHandler(new CustomAuthenticationSuccessHandler("/main")) // 커스텀 핸들러를 사용하기 때문에 CustomAuthenticationSuccessHandler는 직접 구현해야한다.
                  .defaultSuccessUrl("/main")       // 로그인 성공 시 이동하는 페이지
                  .failureUrl("/loginFail")
                  //.failureHandler(new CustomAuthenticationSuccessHandler("/login-fail"))
                  .permitAll()
             }
            .logout {
                it.permitAll()

             }
            .build()

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user: UserDetails =
            User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build()

        return InMemoryUserDetailsManager(user)
    }
}