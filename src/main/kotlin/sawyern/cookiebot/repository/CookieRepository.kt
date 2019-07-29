package sawyern.cookiebot.repository

import org.springframework.data.jpa.repository.JpaRepository
import sawyern.cookiebot.models.Cookie
import java.util.*

interface CookieRepository: JpaRepository<Cookie, String> {
    fun findByAccountDiscordId(discordId: String): Collection<Cookie>
    fun findByAccountDiscordIdAndSeason(discordId: String, season: String): Collection<Cookie>
    fun findByAccountDiscordIdAndTypeAndSeason(discordId: String, type: String, season: String): Collection<Cookie>
}