package sawyern.cookiebot.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import sawyern.cookiebot.models.Account
import java.util.*

@Repository
interface AccountRepository: JpaRepository<Account, String> {
    fun findByDiscordId(discordId: String): Optional<Account>
}