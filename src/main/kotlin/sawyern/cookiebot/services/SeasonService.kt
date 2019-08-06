package sawyern.cookiebot.services

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import sawyern.cookiebot.constants.Constants
import sawyern.cookiebot.exception.CookieException
import sawyern.cookiebot.models.Season
import sawyern.cookiebot.repository.SeasonRepository
import javax.transaction.Transactional

@Service
class SeasonService @Autowired constructor(private val seasonRepository: SeasonRepository) {
    
    private val logger: Logger = LoggerFactory.getLogger(SeasonService::class.java)
    
    fun getCurrentSeason(): Season {
        return seasonRepository.findByStatus(Constants.SEASON_ACTIVE) ?: throw CookieException("No Season found")
    }

    @Transactional
    fun startNewSeason(seasonName: String) {
        val currentSeason = seasonRepository.findByStatus(Constants.SEASON_ACTIVE)

        if (currentSeason != null) {
            currentSeason.status = Constants.SEASON_INACTIVE
            seasonRepository.save(currentSeason)
            logger.info("Ending season ${currentSeason.name}")
        }

        // create new season
        val newSeason = Season(seasonName, Constants.SEASON_ACTIVE)
        seasonRepository.save(newSeason)
        logger.info("Starting season ${newSeason.name}")
    }
}