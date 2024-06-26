package sm.utils

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

class Utils {
    companion object {
        val OBJECT_MAPPER = ObjectMapper()
        val EN_BASE64_URL = Base64.getUrlEncoder()
        val DE_BASE64_URL = Base64.getUrlDecoder()

        inline fun <reified T> logger(): Logger =
            LoggerFactory.getLogger(T::class.java)
    }
}