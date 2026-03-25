package com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.rules
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.rules.ScoringRule
data class TennisState(
    val points: MutableList<Int> = mutableListOf(0, 0),
    val games: MutableList<Int> = mutableListOf(0, 0),
    val advantage: Int? = null
)

class TennisRule : ScoringRule<TennisState> {

    override fun getInitialState(): TennisState {
        return TennisState()
    }

    override fun addPoint(state: TennisState, teamIndex: Int): TennisState {
        val opponent = if (teamIndex == 0) 1 else 0

        var points = state.points.toMutableList()
        var games = state.games.toMutableList()
        var advantage = state.advantage

        // Deuce
        if (points[0] == 3 && points[1] == 3) {
            if (advantage == teamIndex) {
                games[teamIndex]++
                return TennisState()
            }

            if (advantage == opponent) {
                return state.copy(advantage = null)
            }

            return state.copy(advantage = teamIndex)
        }

        points[teamIndex]++

        if (points[teamIndex] > 3) {
            games[teamIndex]++
            return TennisState()
        }

        return state.copy(points = points)
    }
}