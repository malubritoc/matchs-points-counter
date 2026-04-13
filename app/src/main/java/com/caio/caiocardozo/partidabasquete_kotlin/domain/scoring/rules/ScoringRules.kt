import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.model.SportType
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.rules.BasketballRule
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.rules.BeachVolleyballRule
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.rules.RugbyRule
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.rules.ScoringRule
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.rules.SoccerRule
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.rules.TableTennisRule
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.rules.VolleyballRule

object ScoringRules {

    fun getRule(sport: SportType): ScoringRule<*> {
        return when (sport) {
            SportType.BASKETBALL -> BasketballRule()
            SportType.VOLLEYBALL -> VolleyballRule()
            SportType.BEACH_VOLLEYBALL -> BeachVolleyballRule()
            SportType.SOCCER -> SoccerRule()
            SportType.TENNIS -> TennisRule()
            SportType.TABLE_TENNIS -> TableTennisRule()
            SportType.RUGBY -> RugbyRule()

            else -> BasketballRule()
        }
    }
}