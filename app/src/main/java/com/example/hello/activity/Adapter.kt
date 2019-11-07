package com.example.hello.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hello.R

class Adapter: RecyclerView.Adapter<TextItemViewHolder>(){
    // Lista de itens que vão aparecer na tela
    var data = mutableListOf<String>()
        // Notifica quando há alterações nos itens
        set (value) {
            field = value
            notifyDataSetChanged()
        }

    // Tamanho total de itens da lista apresentados na tela
    override fun getItemCount() = data.size

    // Preenche o conteúdo de cada posição que pode ser reciclada
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item
    }

    // Prepara um item que será reciclado
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent,false) as TextView
        return TextItemViewHolder(view)
    }
}