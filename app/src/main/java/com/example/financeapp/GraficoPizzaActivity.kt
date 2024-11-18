package com.example.financeapp

import android.content.Context
import android.database.Cursor
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class GraficoPizzaActivity : AppCompatActivity() {

    private lateinit var pieChart: PieChart
    private lateinit var dbHelper: DatabaseHelper
    private var usuarioId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grafico_pizza)

        // Habilitar o botão "Up"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        pieChart = findViewById(R.id.pieChart)
        dbHelper = DatabaseHelper(this)

        // Obter o usuarioId das SharedPreferences
        val sharedPref = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        usuarioId = sharedPref.getInt("user_id", -1)

        if (usuarioId == -1) {
            finish()
            return
        }

        carregarDadosGrafico()
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

    private fun carregarDadosGrafico() {
        val db = dbHelper.readableDatabase

        val query = "SELECT ${DatabaseHelper.COLUMN_TRANS_CATEGORIA}, SUM(${DatabaseHelper.COLUMN_TRANS_VALOR}) AS total " +
                "FROM ${DatabaseHelper.TABLE_TRANSACAO} " +
                "WHERE ${DatabaseHelper.COLUMN_TRANS_USER_ID} = ? AND ${DatabaseHelper.COLUMN_TRANS_TIPO} = ? " +
                "GROUP BY ${DatabaseHelper.COLUMN_TRANS_CATEGORIA}"

        val cursor: Cursor = db.rawQuery(query, arrayOf(usuarioId.toString(), "Despesa"))

        val entries = ArrayList<PieEntry>()

        while (cursor.moveToNext()) {
            val categoria = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TRANS_CATEGORIA))
            val total = cursor.getFloat(cursor.getColumnIndexOrThrow("total"))
            entries.add(PieEntry(total, categoria))
        }
        cursor.close()

        val dataSet = PieDataSet(entries, "Despesas por Categoria")
        dataSet.colors = listOf(
            Color.BLUE,
            Color.GREEN,
            Color.MAGENTA,
            Color.RED,
            Color.YELLOW,
            Color.CYAN
        )

        val data = PieData(dataSet)
        pieChart.data = data

        val description = Description()
        description.text = "Despesas"
        pieChart.description = description

        pieChart.invalidate() // Atualiza o gráfico
    }
}
