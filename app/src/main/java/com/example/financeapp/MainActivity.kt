package com.example.financeapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnAdicionarTransacao: Button
    private lateinit var btnVerTransacoes: Button
    private lateinit var btnVerGrafico: Button
    private lateinit var btnDefinirMeta: Button
    private lateinit var tvProgressoMeta: TextView
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var sharedPref: SharedPreferences
    private var usuarioId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Definindo o layout
        setContentView(R.layout.activity_main)

        // Conectando os elementos da tela ao código
        btnAdicionarTransacao = findViewById(R.id.btnAdicionarTransacao)
        btnVerTransacoes = findViewById(R.id.btnVerTransacoes)
        btnVerGrafico = findViewById(R.id.btnVerGrafico)
        btnDefinirMeta = findViewById(R.id.btnDefinirMeta)
        tvProgressoMeta = findViewById(R.id.tvProgressoMeta)

        dbHelper = DatabaseHelper(this)
        sharedPref = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        usuarioId = sharedPref.getInt("user_id", -1)

        btnAdicionarTransacao.setOnClickListener {
            val intent = Intent(this, AdicionarTransacaoActivity::class.java)
            startActivity(intent)
        }

        btnVerTransacoes.setOnClickListener {
            val intent = Intent(this, ListaTransacoesActivity::class.java)
            startActivity(intent)
        }

        btnVerGrafico.setOnClickListener {
            val intent = Intent(this, GraficoPizzaActivity::class.java)
            startActivity(intent)
        }

        btnDefinirMeta.setOnClickListener {
            val intent = Intent(this, DefinirMetaActivity::class.java)
            startActivity(intent)
        }

        atualizarProgressoMeta()
    }

    override fun onResume() {
        super.onResume()
        atualizarProgressoMeta()
    }

    private fun atualizarProgressoMeta() {
        val valorMeta = sharedPref.getFloat("valor_meta", 0f)
        if (valorMeta > 0 && usuarioId != -1) {
            val db = dbHelper.readableDatabase

            val cursor: Cursor = db.rawQuery(
                "SELECT SUM(${DatabaseHelper.COLUMN_TRANS_VALOR}) AS total " +
                        "FROM ${DatabaseHelper.TABLE_TRANSACAO} " +
                        "WHERE ${DatabaseHelper.COLUMN_TRANS_USER_ID} = ? AND ${DatabaseHelper.COLUMN_TRANS_TIPO} = ?",
                arrayOf(usuarioId.toString(), "Receita")
            )

            var totalReceitas = 0f
            if (cursor.moveToFirst()) {
                totalReceitas = cursor.getFloat(cursor.getColumnIndexOrThrow("total"))
            }
            cursor.close()

            val progresso = (totalReceitas / valorMeta) * 100
            tvProgressoMeta.text = "Progresso da Meta: ${String.format("%.2f", progresso)}%"
        } else {
            tvProgressoMeta.text = "Nenhuma meta definida."
        }
    }
}
