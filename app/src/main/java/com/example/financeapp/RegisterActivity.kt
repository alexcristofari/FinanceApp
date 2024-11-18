package com.example.financeapp

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var etNome: EditText
    private lateinit var etEmail: EditText
    private lateinit var etSenha: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Definindo qual layout usar
        setContentView(R.layout.activity_register)

        // Conectando os elementos da tela ao código
        etNome = findViewById(R.id.etNome)
        etEmail = findViewById(R.id.etEmail)
        etSenha = findViewById(R.id.etSenha)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        // Inicializando o banco de dados
        dbHelper = DatabaseHelper(this)

        // O que acontece quando clicamos no botão
        btnRegistrar.setOnClickListener {
            val nome = etNome.text.toString()
            val email = etEmail.text.toString()
            val senha = etSenha.text.toString()

            if (nome.isNotEmpty() && email.isNotEmpty() && senha.isNotEmpty()) {
                val db = dbHelper.writableDatabase

                val values = ContentValues().apply {
                    put(DatabaseHelper.COLUMN_USER_NAME, nome)
                    put(DatabaseHelper.COLUMN_USER_EMAIL, email)
                    put(DatabaseHelper.COLUMN_USER_PASSWORD, senha)
                }

                val newRowId = db.insert(DatabaseHelper.TABLE_USER, null, values)

                if (newRowId != -1L) {
                    Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                    finish() // Fecha a atividade atual
                } else {
                    Toast.makeText(this, "Erro ao cadastrar usuário.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
