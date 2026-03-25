package com.caio.caiocardozo.partidabasquete_kotlin.ui.scoreboard.renderer

import android.view.*
import android.widget.*
import com.caio.caiocardozo.partidabasquete_kotlin.R
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.rules.TennisState

class TennisRenderer(
    private val onAddPoint: (Int) -> Unit
) : ScoreRenderer<TennisState> {

    private lateinit var scoreA: TextView
    private lateinit var scoreB: TextView

    private val pointsMap = listOf("0", "15", "30", "40")

    override fun createView(container: ViewGroup): View {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.view_tennis, container, false)

        scoreA = view.findViewById(R.id.scoreA)
        scoreB = view.findViewById(R.id.scoreB)

        view.findViewById<Button>(R.id.btnA).setOnClickListener {
            onAddPoint(0)
        }

        view.findViewById<Button>(R.id.btnB).setOnClickListener {
            onAddPoint(1)
        }

        return view
    }

    override fun bind(state: TennisState) {
        scoreA.text = pointsMap.getOrElse(state.points[0]) { "40" }
        scoreB.text = pointsMap.getOrElse(state.points[1]) { "40" }
    }
}