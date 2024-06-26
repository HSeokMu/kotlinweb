package sm

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import sm.utils.Utils

@SpringBootApplication
class Application : CommandLineRunner {
	var log = Utils.logger<Application>()

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<Application>(*args)
		}
	}

	override fun run(vararg args: String?) {
		log.info("start server")
	}
}
