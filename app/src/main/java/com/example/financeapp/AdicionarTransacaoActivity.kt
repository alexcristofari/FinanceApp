package com.example.financeapp

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdicionarTransacaoActivity : AppCompatActivity() {

    private lateinit var etTipo: EditText
    private lateinit var etValor: EditText
    private lateinit var etData: EditText
    private lateinit var etCategoria: EditText
    private lateinit var etDescricao: EditText
    private lateinit var btnSalvarTransacao: Button
    private lateinit var dbHelper: DatabaseHelper
    private var usuarioId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Definindo o layout
        setContentView(R.layout.activity_adicionar_transacao)

        // Habilitar o botão "Up"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Conectando os elementos da tela ao código
        etTipo = findViewById(R.id.etTipo)
        etValor = findViewById(R.id.etValor)
        etData = findViewById(R.id.etData)
        etCategoria = findViewById(R.id.etCategoria)
        etDescricao = findViewById(R.id.etDescricao)
        btnSalvarTransacao = findViewById(R.id.btnSalvarTransacao)

        dbHelper = DatabaseHelper(this)

        // Obter o usuarioId das SharedPreferences
        val sharedPref = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        usuarioId = sharedPref.getInt("user_id", -1)

        if (usuarioId == -1) {
            Toast.makeText(this, "Erro: usuário não identificado.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // O que acontece quando clicamos no botão
        btnSalvarTransacao.setOnClickListener {
            val tipo = etTipo.text.toString().trim()
            val valorStr = etValor.text.toString().trim()
            val data = etData.text.toString().trim()
            val categoria = etCategoria.text.toString().trim()
            val descricao = etDescricao.text.toString().trim()

            if (tipo.isNotEmpty() && valorStr.isNotEmpty() && data.isNotEmpty() && categoria.isNotEmpty()) {
                val valor = valorStr.toDoubleOrNull()
                if (valor != null) {
                    val db = dbHelper.writableDatabase

                    val values = ContentValues().apply {
                        put(DatabaseHelper.COLUMN_TRANS_TIPO, tipo)
                        put(DatabaseHelper.COLUMN_TRANS_VALOR, valor)
                        put(DatabaseHelper.COLUMN_TRANS_DATA, data)
                        put(DatabaseHelper.COLUMN_TRANS_CATEGORIA, categoria)
                        put(DatabaseHelper.COLUMN_TRANS_DESCRICAO, descricao)
                        put(DatabaseHelper.COLUMN_TRANS_USER_ID, usuarioId)
                    }

                    val newRowId = db.insert(DatabaseHelper.TABLE_TRANSACAO, null, values)

                    if (newRowId != -1L) {
                        Toast.makeText(this, "Transação salva com sucesso!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Erro ao salvar transação.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Por favor, insira um valor numérico válido.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
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
