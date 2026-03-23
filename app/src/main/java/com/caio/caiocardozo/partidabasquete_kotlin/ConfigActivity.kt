package com.caio.caiocardozo.partidabasquete_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class ConfigActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        val esporte = intent.getStringExtra("ESPORTE")

        val spinner: Spinner = findViewById(R.id.qtdTimes)

        val timeA = findViewById<EditText>(R.id.timeA)
        val timeB = findViewById<EditText>(R.id.timeB)
        val timeC = findViewById<EditText>(R.id.timeC)
        val timeD = findViewById<EditText>(R.id.timeD)

        val limite = findViewById<EditText>(R.id.limitePontos)
        val tempo = findViewById<EditText>(R.id.tempo)

        val btn = findViewById<Button>(R.id.btnIniciar)

        // Spinner setup
        val opcoes = arrayOf("2", "3", "4")

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            opcoes
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Mostrar/esconder campos dinamicamente
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val qtd = parent.getItemAtPosition(position).toString().toInt()

                timeC.visibility = if (qtd >= 3) View.VISIBLE else View.GONE
                timeD.visibility = if (qtd >= 4) View.VISIBLE else View.GONE
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Botão iniciar
        btn.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("ESPORTE", esporte)
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