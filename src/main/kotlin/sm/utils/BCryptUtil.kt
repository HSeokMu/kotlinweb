package sm.utils
import org.springframework.security.crypto.bcrypt.BCrypt

class BCryptUtil {
    companion object {
        val salt: String = BCrypt.gensalt(10)
        fun encode(s: String): String = BCrypt.hashpw(s, salt)
        fun checkpw(s: String, text: String): Boolean = BCrypt.checkpw(s, text)
    }
}