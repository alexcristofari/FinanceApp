package com.example.financeapp

data class Transacao(
    val id: Int,
    val tipo: String,
    val valor: Double,
    val data: String,
    val categoria: String,
    val descricao: String,
    val usuarioId: Int
)
