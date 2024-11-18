package com.example.financeapp

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ListaTransacoesActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var dbHelper: DatabaseHelper
    private var transacoesList: ArrayList<Transacao> = ArrayList()
    private var usuarioId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        // Habilitar o botão "Up"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        listView = findViewById(R.id.listaTransacoes)
        dbHelper = DatabaseHelper(this)

        // Obter o usuarioId das SharedPreferences
        val sharedPref = getSharedPreferences("USER_DATA", MODE_PRIVATE)
        usuarioId = sharedPref.getInt("user_id", -1)

        if (usuarioId == -1) {
            Toast.makeText(this, "Erro: usuário não identificado.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        carregarTransacoes()

        listView.setOnItemClickListener { _, _, position, _ ->
            val transacaoSelecionada = transacoesList[position]
            val intent = Intent(this, EditarTransacaoActivity::class.java)
            intent.putExtra("transacao_id", transacaoSelecionada.id)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        carregarTransacoes()
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

    private fun carregarTransacoes() {
        transacoesList.clear()
        val db = dbHelper.readableDatabase

        val cursor: Cursor = db.query(
            DatabaseHelper.TABLE_TRANSACAO,
            null,
            "${DatabaseHelper.COLUMN_TRANS_USER_ID} = ?",
            arrayOf(usuarioId.toString()),
            null,
            null,
            "${DatabaseHelper.COLUMN_TRANS_DATA} DESC"
        )

        if (cursor.moveToFirst()) {
            do {
                val transacao = Transacao(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TRANS_ID)),
                    tipo = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TRANS_TIPO)),
                    valor = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TRANS_VALOR)),
                    data = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TRANS_DATA)),
                    categoria = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TRANS_CATEGORIA)),
                    descricao = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TRANS_DESCRICAO)),
                    usuarioId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TRANS_USER_ID))
                )
                transacoesList.add(transacao)
            } while (cursor.moveToNext())
        }
        cursor.close()

        val adapter = TransacaoAdapter(this, transacoesList)
        listView.adapter = adapter
    }
}
