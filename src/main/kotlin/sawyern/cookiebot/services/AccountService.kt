package sawyern.cookiebot.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import sawyern.cookiebot.exception.CookieException
import sawyern.cookiebot.models.Account
import sawyern.cookiebot.repository.AccountRepository

@Service
class AccountService @Autowired constructor(private val accountRepository: AccountRepository) {

    fun registerAccount(discordId: String, username: String) {
        accountRepository.findByDiscordId(discordId).ifPresent { throw CookieException("Account already registered") }

        val account = Account(discordId, username)
        accountRepository.save(account)
    }

    fun getAccount(discordId: String): Account {
        return accountRepository.findByDiscordId(discordId)
                .orElseThrow { CookieException("Unable to find account with id $discordId") }
    }

    fun deleteAccount(discordId: String) {
        val account = accountRepository.findByDiscordId(discordId).orElseThrow { CookieException("Account does not exist therefore cannot be deleted") }
        accountRepository.delete(account)
    }

    fun findAll(): List<Account> {
        return accountRepository.findAll()
    }
}