package sm.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import sm.interceptor.HibernateEventListener

@Configuration
class BeanConfig {
    /*@Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }*/

    @Bean
    fun sqlEventListener(): HibernateEventListener {
        return HibernateEventListener()
    }
}