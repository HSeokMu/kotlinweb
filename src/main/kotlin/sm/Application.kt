package sm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SmWebCoreApplication

fun main(args: Array<String>) {
	runApplication<SmWebCoreApplication>(*args)
}
