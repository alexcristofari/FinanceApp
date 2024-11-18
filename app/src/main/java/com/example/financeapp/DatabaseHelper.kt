package com.example.financeapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {

    companion object {
        const val DATABASE_NAME = "finance_app.db"
        const val DATABASE_VERSION = 1

        // Tabela de Usuários
        const val TABLE_USER = "usuario"
        const val COLUMN_USER_ID = "id"
        const val COLUMN_USER_NAME = "nome"
        const val COLUMN_USER_EMAIL = "email"
        const val COLUMN_USER_PASSWORD = "senha"

        // Tabela de Transações
        const val TABLE_TRANSACAO = "transacao"
        const val COLUMN_TRANS_ID = "id"
        const val COLUMN_TRANS_TIPO = "tipo"
        const val COLUMN_TRANS_VALOR = "valor"
        const val COLUMN_TRANS_DATA = "data"
        const val COLUMN_TRANS_CATEGORIA = "categoria"
        const val COLUMN_TRANS_DESCRICAO = "descricao"
        const val COLUMN_TRANS_USER_ID = "usuario_id"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_USER = ("CREATE TABLE $TABLE_USER ("
                + "$COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_USER_NAME TEXT, "
                + "$COLUMN_USER_EMAIL TEXT UNIQUE, "
                + "$COLUMN_USER_PASSWORD TEXT)")

        val CREATE_TABLE_TRANSACAO = ("CREATE TABLE $TABLE_TRANSACAO ("
                + "$COLUMN_TRANS_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_TRANS_TIPO TEXT, "
                + "$COLUMN_TRANS_VALOR REAL, "
                + "$COLUMN_TRANS_DATA TEXT, "
                + "$COLUMN_TRANS_CATEGORIA TEXT, "
                + "$COLUMN_TRANS_DESCRICAO TEXT, "
                + "$COLUMN_TRANS_USER_ID INTEGER, "
                + "FOREIGN KEY($COLUMN_TRANS_USER_ID) REFERENCES $TABLE_USER($COLUMN_USER_ID))")

        db?.execSQL(CREATE_TABLE_USER)
        db?.execSQL(CREATE_TABLE_TRANSACAO)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_TRANSACAO")
        onCreate(db)
    }
}
