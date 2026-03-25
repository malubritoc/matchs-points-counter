package com.caio.caiocardozo.partidabasquete_kotlin.ui.scoreboard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.caio.caiocardozo.partidabasquete_kotlin.R
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.model.SportType
import com.caio.caiocardozo.partidabasquete_kotlin.ui.modality.PickModality
import com.caio.caiocardozo.partidabasquete_kotlin.ui.scoreboard.renderer.*

class ScoreActivity : AppCompatActivity() {

    private lateinit var container: FrameLayout
    private lateinit var viewModel: ScoreViewModel

    private var renderer: ScoreRenderer<Any>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_score)

        container = findViewById(R.id.container)

        val btnFinish = findViewById<Button>(R.id.btnFinishGame)
        btnFinish.setOnClickListener {
            showFinishDialog()
        }

        val sportString = intent.getStringExtra("SPORT")

        val sport = try {
            SportType.valueOf(sportString ?: "")
        } catch (e: Exception) {
            SportType.BASKETBALL
        }

        println("DEBUG SPORT RECEBIDO: $sport")

        viewModel = ViewModelProvider(this)[ScoreViewModel::class.java]

        setupRenderer(sport)
        viewModel.startGame(sport)

        viewModel.state.observe(this) { state ->
            renderer?.bind(state)
        }
    }

    private fun setupRenderer(sport: SportType) {

        container.removeAllViews()

        renderer = when (sport) {
            SportType.BASKETBALL -> BasketballRenderer { team, points ->
                viewModel.addPoints(team, points)
            }

            SportType.VOLLEYBALL -> VolleyballRenderer {
                viewModel.addPoint(it)
            }

            SportType.TENNIS -> TennisRenderer {
                viewModel.addPoint(it)
            }

            else -> BasketballRenderer { team, points ->
                viewModel.addPoints(team, points)
            }
        } as ScoreRenderer<Any>

        val view = renderer!!.createView(container)
        container.addView(view)
    }

    private fun showFinishDialog() {
        AlertDialog.Builder(this)
            .setTitle("Encerrar partida")
            .setMessage("Tem certeza que deseja finalizar?")
            .setPositiveButton("Sim") { _, _ ->
                finishGame()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun finishGame() {
        val intent = Intent(this, PickModality::class.java)

        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK

        startActivity(intent)
        finish()
    }
}