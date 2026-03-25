package com.caio.caiocardozo.partidabasquete_kotlin.ui.modality

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.caio.caiocardozo.partidabasquete_kotlin.R
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.model.SportType
import com.caio.caiocardozo.partidabasquete_kotlin.ui.config.ConfigActivity

class PickModality : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_modality)

        findViewById<LinearLayout>(R.id.cardBasquete).setOnClickListener {
            navegar(SportType.BASKETBALL)
        }

//        findViewById<LinearLayout>(R.id.cardFutebol).setOnClickListener {
//            navegar(SportType.SOCCER)
//        }

        findViewById<LinearLayout>(R.id.cardVolei).setOnClickListener {
            navegar(SportType.VOLLEYBALL)
        }

//        findViewById<LinearLayout>(R.id.cardVoleiDeAreia).setOnClickListener {
//            navegar(SportType.BEACH_VOLLEYBALL)
//        }

        findViewById<LinearLayout>(R.id.cardTenis).setOnClickListener {
            navegar(SportType.TENNIS)
        }

//        findViewById<LinearLayout>(R.id.cardTenisDeMesa).setOnClickListener {
//            navegar(SportType.TABLE_TENNIS)
//        }

//        findViewById<LinearLayout>(R.id.cardSinuca).setOnClickListener {
//            navegar(SportType.BILLIARDS)
//        }
//
//        findViewById<LinearLayout>(R.id.cardLivre).setOnClickListener {
//            navegar(SportType.CUSTOM)
//        }
    }

    private fun navegar(esporte: SportType) {
        val intent = Intent(this, ConfigActivity::class.java)
        intent.putExtra("ESPORTE", esporte.name)
        startActivity(intent)
    }
}