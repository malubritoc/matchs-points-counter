package com.caio.caiocardozo.partidabasquete_kotlin.ui.config

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.caio.caiocardozo.partidabasquete_kotlin.R
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.model.SportType
import com.caio.caiocardozo.partidabasquete_kotlin.ui.scoreboard.ScoreActivity

class ConfigActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        val esporteString = intent.getStringExtra("ESPORTE")

        val spinner: Spinner = findViewById(R.id.qtdTimes)

        val timeA = findViewById<EditText>(R.id.timeA)
        val timeB = findViewById<EditText>(R.id.timeB)
        val timeC = findViewById<EditText>(R.id.timeC)
        val timeD = findViewById<EditText>(R.id.timeD)

        val limite = findViewById<EditText>(R.id.limitePontos)
        val tempo = findViewById<EditText>(R.id.tempo)

        val btn = findViewById<Button>(R.id.btnIniciar)

        val opcoes = arrayOf("2", "3", "4")

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            opcoes
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val qtd = parent.getItemAtPosition(position).toString().toInt()

                timeC.visibility = if (qtd >= 3) View.VISIBLE else View.GONE
                timeD.visibility = if (qtd >= 4) View.VISIBLE else View.GONE
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        btn.setOnClickListener {

            val sport = when (esporteString?.trim()?.uppercase()) {
                "BASQUETE", "BASKETBALL" -> SportType.BASKETBALL
                "VOLEI", "VOLLEYBALL" -> SportType.VOLLEYBALL
                "TENIS", "TENNIS" -> SportType.TENNIS
                else -> {
                    println("DEBUG ERRO ESPORTE: $esporteString")
                    SportType.BASKETBALL
                }
            }

            val intent = Intent(this, ScoreActivity::class.java)

            intent.putExtra("SPORT", sport.name)
            intent.putExtra("QTD_TIMES", spinner.selectedItem.toString().toInt())

            intent.putExtra("TIME_A", timeA.text.toString())
            intent.putExtra("TIME_B", timeB.text.toString())
            intent.putExtra("TIME_C", timeC.text.toString())
            intent.putExtra("TIME_D", timeD.text.toString())

            intent.putExtra("LIMITE", limite.text.toString())
            intent.putExtra("TEMPO", tempo.text.toString())

            startActivity(intent)
        }
    }
}