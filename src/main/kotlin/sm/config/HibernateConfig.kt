package sm.config

import jakarta.annotation.PostConstruct
import jakarta.persistence.EntityManagerFactory
import org.hibernate.event.service.spi.EventListenerRegistry
import org.hibernate.event.spi.EventType
import org.hibernate.internal.SessionFactoryImpl
import org.springframework.context.annotation.Configuration
import sm.interceptor.HibernateEventListener

@Configuration
class HibernateConfig(
    private val entityManagerFactory: EntityManagerFactory,
    private val hibernateEventListener: HibernateEventListener
) {

    @PostConstruct
    fun registerListeners() {
        val sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl::class.java)
        val registry = sessionFactory.serviceRegistry.getService(EventListenerRegistry::class.java)
        registry?.getEventListenerGroup(EventType.PRE_INSERT)?.appendListener(hibernateEventListener)
        registry?.getEventListenerGroup(EventType.PRE_UPDATE)?.appendListener(hibernateEventListener)
    }
}