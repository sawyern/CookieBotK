package sawyern.cookiebot.services

import discord4j.core.DiscordClient
import discord4j.core.DiscordClientBuilder
import discord4j.core.`object`.entity.MessageChannel
import discord4j.core.`object`.util.Snowflake
import discord4j.core.event.domain.lifecycle.ReadyEvent
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import sawyern.cookiebot.commands.BotCommand
import sawyern.cookiebot.properties.DiscordClientProperties

@Service
class DiscordService @Autowired constructor(
        private val discordClientProperties: DiscordClientProperties,
        private val botCommands: List<BotCommand>
) {
    private val discordClient: DiscordClient = DiscordClientBuilder(discordClientProperties.token).build()
    private val logger = KotlinLogging.logger {}

    fun subscribeAllCommands() = botCommands.forEach { command -> command.subscribe(discordClient) }

    fun subscribeReady() {
        discordClient.eventDispatcher
                .on(ReadyEvent::class.java)
                .subscribe { logger.info("Logged in as {}", it.self.username) }
    }

    fun login() = discordClient.login().block()

    fun getBotChannel(): MessageChannel {
        return discordClient
                .getChannelById(Snowflake.of(discordClientProperties.botChannelId))
                .block()
                as MessageChannel
    }
}



