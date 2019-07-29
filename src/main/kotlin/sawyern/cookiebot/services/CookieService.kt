package sawyern.cookiebot.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import sawyern.cookiebot.exception.CookieException
import sawyern.cookiebot.models.Cookie
import sawyern.cookiebot.repository.CookieRepository

@Service
class CookieService @Autowired constructor(
        val cookieRepository: CookieRepository,
        val seasonService: SeasonService,
        @Lazy
        val accountService: AccountService

) {
    fun giveCookieTo(senderId: String, receiverId: String, numCookies: Int) {
        if (numCookies <= 0)
            throw CookieException("Invalid number of cookies: $numCookies")

        val senderCookies = cookieRepository.findByAccountDiscordIdAndSeason(senderId, seasonService.getCurrentSeason())

        if (senderCookies.size < numCookies)
            throw CookieException("Not enough cookies to give")

        var cookiesGiven = 0
        val receiverAccount = accountService.getAccount(receiverId)
        for (cookie in senderCookies) {
            cookie.account = receiverAccount
            cookieRepository.save(cookie)
            cookiesGiven++

            if (cookiesGiven == numCookies)
                break
        }
    }

    fun getCookiesForAccount(discordId: String, season: String = seasonService.getCurrentSeason()): Int {
        return cookieRepository.findByAccountDiscordIdAndSeason(discordId, season).size
    }

    fun generateCookie(discordId: String, source: String = "Unknown", numCookies: Int = 1) {
        val account = accountService.getAccount(discordId)

        for(i in 1..numCookies) {
            val cookie = Cookie(account, seasonService.getCurrentSeason(), source)
            cookieRepository.save(cookie)
        }
    }
}