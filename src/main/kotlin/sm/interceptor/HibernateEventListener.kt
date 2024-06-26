package sm.interceptor

import org.hibernate.event.spi.PreInsertEvent
import org.hibernate.event.spi.PreInsertEventListener
import org.hibernate.event.spi.PreUpdateEvent
import org.hibernate.event.spi.PreUpdateEventListener
import sm.utils.Utils

class HibernateEventListener : PreInsertEventListener, PreUpdateEventListener {
    private val log = Utils.logger<HibernateEventListener>()

    override fun onPreInsert(event: PreInsertEvent): Boolean {
        logEvent(event.entity, event.state, event.persister.propertyNames)
        return false
    }

    override fun onPreUpdate(event: PreUpdateEvent): Boolean {
        logEvent(event.entity, event.state, event.persister.propertyNames)
        return false
    }

    private fun logEvent(entity: Any?, state: Array<Any?>, propertyNames: Array<String>) {
        val sb = StringBuffer("\n------------ Hibernate Parameter start ---------------\n")
        sb.append("Entity: ${entity?.javaClass?.name}").append("\n")
        for (i in propertyNames.indices) {
            sb.append("Property[${propertyNames[i]}]: ${state[i]}").append("\n")
        }
        sb.append("------------ Hibernate Parameter End ---------------\n")
        log.info(sb.toString())
    }
}