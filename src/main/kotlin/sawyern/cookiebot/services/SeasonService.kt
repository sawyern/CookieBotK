package sawyern.cookiebot.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import sawyern.cookiebot.exception.CookieException
import sawyern.cookiebot.models.Season
import sawyern.cookiebot.repository.SeasonRepository
import javax.transaction.Transactional

@Service
class SeasonService @Autowired constructor(val seasonRepository: SeasonRepository) {
    fun getCurrentSeason(): String {
        val season = seasonRepository.findByActive().orElseThrow { CookieException("No Season found") }
        return season.name
    }

    @Transactional
    fun startNewSeason(seasonName: String) {
        val currentSeason = seasonRepository.findByActive()

        // invalidate current season
        if (currentSeason.isPresent) {
            currentSeason.get().active = false
            seasonRepository.save(currentSeason.get())
        }

        // create new season
        val newSeason = Season(seasonName, true)
        seasonRepository.save(newSeason)
    }
}