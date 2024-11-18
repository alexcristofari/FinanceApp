package com.example.financeapp

import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etSenha: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnIrParaCadastro: Button
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Definindo o layout
        setContentView(R.layout.activity_login)

        // Conectando os elementos da tela ao código
        etEmail = findViewById(R.id.etEmail)
        etSenha = findViewById(R.id.etSenha)
        btnLogin = findViewById(R.id.btnLogin)
        btnIrParaCadastro = findViewById(R.id.btnIrParaCadastro)

        // Inicializando o banco de dados
        dbHelper = DatabaseHelper(this)

        // Inicializando o SharedPreferences
        sharedPref = getSharedPreferences("USER_DATA", MODE_PRIVATE)

        // Botão para ir para a tela de cadastro
        btnIrParaCadastro.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // O que acontece quando clicamos no botão de login
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val senha = etSenha.text.toString()

            if (email.isNotEmpty() && senha.isNotEmpty()) {
                val db = dbHelper.readableDatabase

                val projection = arrayOf(DatabaseHelper.COLUMN_USER_ID)
                val selection = "${DatabaseHelper.COLUMN_USER_EMAIL} = ? AND ${DatabaseHelper.COLUMN_USER_PASSWORD} = ?"
                val selectionArgs = arrayOf(email, senha)

                val cursor: Cursor = db.query(
                    DatabaseHelper.TABLE_USER,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
                )

                if (cursor.moveToFirst()) {
                    // Login bem-sucedido
                    val userId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_ID))

                    // Salvar o userId no SharedPreferences
                    val editor = sharedPref.edit()
                    editor.putInt("user_id", userId)
                    editor.apply()

                    Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Email ou senha incorretos.", Toast.LENGTH_SHORT).show()
                }
                cursor.close()
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
