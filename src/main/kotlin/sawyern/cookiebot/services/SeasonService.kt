package sawyern.cookiebot.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import sawyern.cookiebot.constants.Constants
import sawyern.cookiebot.exception.CookieException
import sawyern.cookiebot.models.Season
import sawyern.cookiebot.repository.SeasonRepository
import javax.transaction.Transactional

@Service
class SeasonService @Autowired constructor(private val seasonRepository: SeasonRepository) {
    fun getCurrentSeason(): String {
        val season = seasonRepository.findByStatus(Constants.SEASON_ACTIVE).orElseThrow { CookieException("No Season found") }
        return season.name
    }

    @Transactional
    fun startNewSeason(seasonName: String) {
        val currentSeason = seasonRepository.findByStatus(Constants.SEASON_ACTIVE)

        // invalidate current season
        if (currentSeason.isPresent) {
            currentSeason.get().status = Constants.SEASON_INACTIVE
            seasonRepository.save(currentSeason.get())
        }

        // create new season
        val newSeason = Season(seasonName, Constants.SEASON_ACTIVE)
        seasonRepository.save(newSeason)
    }
}