package com.example.financeapp

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditarTransacaoActivity : AppCompatActivity() {

    private lateinit var etTipo: EditText
    private lateinit var etValor: EditText
    private lateinit var etData: EditText
    private lateinit var etCategoria: EditText
    private lateinit var etDescricao: EditText
    private lateinit var btnAtualizarTransacao: Button
    private lateinit var btnExcluirTransacao: Button
    private lateinit var dbHelper: DatabaseHelper
    private var transacaoId: Int = -1
    private var usuarioId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Definindo o layout
        setContentView(R.layout.activity_editar_transacao)

        // Habilitar o botão "Up"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        etTipo = findViewById(R.id.etTipo)
        etValor = findViewById(R.id.etValor)
        etData = findViewById(R.id.etData)
        etCategoria = findViewById(R.id.etCategoria)
        etDescricao = findViewById(R.id.etDescricao)
        btnAtualizarTransacao = findViewById(R.id.btnAtualizarTransacao)
        btnExcluirTransacao = findViewById(R.id.btnExcluirTransacao)

        dbHelper = DatabaseHelper(this)

        // Obter o usuarioId das SharedPreferences
        val sharedPref = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        usuarioId = sharedPref.getInt("user_id", -1)

        if (usuarioId == -1) {
            Toast.makeText(this, "Erro: usuário não identificado.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        transacaoId = intent.getIntExtra("transacao_id", -1)

        if (transacaoId == -1) {
            Toast.makeText(this, "Erro: transação não encontrada.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        carregarTransacao()

        btnAtualizarTransacao.setOnClickListener {
            atualizarTransacao()
        }

        btnExcluirTransacao.setOnClickListener {
            excluirTransacao()
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

    private fun carregarTransacao() {
        val db = dbHelper.readableDatabase

        val cursor = db.query(
            DatabaseHelper.TABLE_TRANSACAO,
            null,
            "${DatabaseHelper.COLUMN_TRANS_ID} = ? AND ${DatabaseHelper.COLUMN_TRANS_USER_ID} = ?",
            arrayOf(transacaoId.toString(), usuarioId.toString()),
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            etTipo.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TRANS_TIPO)))
            etValor.setText(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TRANS_VALOR)).toString())
            etData.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TRANS_DATA)))
            etCategoria.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TRANS_CATEGORIA)))
            etDescricao.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TRANS_DESCRICAO)))
        } else {
            Toast.makeText(this, "Erro ao carregar transação.", Toast.LENGTH_SHORT).show()
            finish()
        }
        cursor.close()
    }

    private fun atualizarTransacao() {
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
                }

                val rowsAffected = db.update(
                    DatabaseHelper.TABLE_TRANSACAO,
                    values,
                    "${DatabaseHelper.COLUMN_TRANS_ID} = ? AND ${DatabaseHelper.COLUMN_TRANS_USER_ID} = ?",
                    arrayOf(transacaoId.toString(), usuarioId.toString())
                )

                if (rowsAffected > 0) {
                    Toast.makeText(this, "Transação atualizada com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Erro ao atualizar transação.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, insira um valor numérico válido.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun excluirTransacao() {
        val db = dbHelper.writableDatabase

        val rowsDeleted = db.delete(
            DatabaseHelper.TABLE_TRANSACAO,
            "${DatabaseHelper.COLUMN_TRANS_ID} = ? AND ${DatabaseHelper.COLUMN_TRANS_USER_ID} = ?",
            arrayOf(transacaoId.toString(), usuarioId.toString())
        )

        if (rowsDeleted > 0) {
            Toast.makeText(this, "Transação excluída com sucesso!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Erro ao excluir transação.", Toast.LENGTH_SHORT).show()
        }
    }
}
