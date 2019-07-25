package sawyern.cookiebot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CookiebotApplication

fun main(args: Array<String>) {
	runApplication<CookiebotApplication>(*args)
}
