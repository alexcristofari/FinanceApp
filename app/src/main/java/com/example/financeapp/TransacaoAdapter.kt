package com.example.financeapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class TransacaoAdapter(private val context: Context, private val transacoes: ArrayList<Transacao>) :
    BaseAdapter() {

    override fun getCount(): Int {
        return transacoes.size
    }

    override fun getItem(position: Int): Any {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return transacoes[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val transacao = transacoes[position]
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.item_transacao, parent, false)

        val tvTipoValor = rowView.findViewById<TextView>(R.id.tvTipoValor)
        val tvData = rowView.findViewById<TextView>(R.id.tvData)
        val tvCategoriaDescricao = rowView.findViewById<TextView>(R.id.tvCategoriaDescricao)

        tvTipoValor.text = "${transacao.tipo} - R$ ${transacao.valor}"
        tvData.text = transacao.data
        tvCategoriaDescricao.text = "${transacao.categoria} - ${transacao.descricao}"

        return rowView
    }
}
