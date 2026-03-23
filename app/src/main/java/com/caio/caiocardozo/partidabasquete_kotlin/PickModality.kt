package com.caio.caiocardozo.partidabasquete_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class PickModality : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_modality)

        // Basquete
        findViewById<LinearLayout>(R.id.cardBasquete).setOnClickListener {
            navegar("BASQUETE")
        }

        // Futebol
        findViewById<LinearLayout>(R.id.cardFutebol).setOnClickListener {
            navegar("FUTEBOL")
        }

        // Vôlei
        findViewById<LinearLayout>(R.id.cardVolei).setOnClickListener {
            navegar("VOLEI")
        }

        // Vôlei de Areia
        findViewById<LinearLayout>(R.id.cardVoleiDeAreia).setOnClickListener {
            navegar("VOLEI_AREIA")
        }

        // Tênis
        findViewById<LinearLayout>(R.id.cardTenis).setOnClickListener {
            navegar("TENIS")
        }

        // Tênis de Mesa
        findViewById<LinearLayout>(R.id.cardTenisDeMesa).setOnClickListener {
            navegar("TENIS_MESA")
        }

        // Sinuca
        findViewById<LinearLayout>(R.id.cardSinuca).setOnClickListener {
            navegar("SINUCA")
        }

        // Livre
        findViewById<LinearLayout>(R.id.cardLivre).setOnClickListener {
            navegar("LIVRE")
        }
    }

    private fun navegar(esporte: String) {
        val intent = Intent(this, ConfigActivity::class.java)
        intent.putExtra("ESPORTE", esporte)
        startActivity(intent)
    }
}