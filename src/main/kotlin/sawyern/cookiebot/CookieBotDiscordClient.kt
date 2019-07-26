package sawyern.cookiebot

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import sawyern.cookiebot.services.DiscordService

@Component
class CookieBotDiscordClient @Autowired
constructor(val discordService: DiscordService) {
    private val logger = KotlinLogging.logger {}

    @EventListener(ApplicationReadyEvent::class)
    fun startDiscordClient() {
        discordService.subscribeReady()
        discordService.subscribeAllCommands()
        discordService.login()
    }
}
