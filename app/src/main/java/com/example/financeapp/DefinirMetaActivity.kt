package com.example.financeapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DefinirMetaActivity : AppCompatActivity() {

    private lateinit var etValorMeta: EditText
    private lateinit var btnSalvarMeta: Button
    private lateinit var sharedPref: SharedPreferences
    private var usuarioId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_definir_meta)

        // Habilitar o botão "Up"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        etValorMeta = findViewById(R.id.etValorMeta)
        btnSalvarMeta = findViewById(R.id.btnSalvarMeta)

        sharedPref = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        usuarioId = sharedPref.getInt("user_id", -1)

        if (usuarioId == -1) {
            finish()
            return
        }

        // Carregar valor da meta, se existir
        val valorMeta = sharedPref.getFloat("valor_meta", 0f)
        if (valorMeta > 0) {
            etValorMeta.setText(valorMeta.toString())
        }

        btnSalvarMeta.setOnClickListener {
            val valorMetaStr = etValorMeta.text.toString().trim()
            val valorMeta = valorMetaStr.toFloatOrNull()
            if (valorMeta != null && valorMeta > 0) {
                val editor = sharedPref.edit()
                editor.putFloat("valor_meta", valorMeta)
                editor.apply()
                Toast.makeText(this, "Meta salva com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Por favor, insira um valor válido.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Tratar o clique no botão "Up"
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish() // Encerra a atividade atual
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
