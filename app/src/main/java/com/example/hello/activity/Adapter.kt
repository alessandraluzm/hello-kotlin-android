package com.example.hello.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hello.databinding.TextItemViewBinding

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {
    // Lista de itens que vão aparecer na tela
    var data = mutableListOf<Tarefa>()
        // Notifica quando há alterações nos itens
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    // Tamanho total de itens da lista apresentados na tela
    override fun getItemCount() = data.size

    // Preenche o conteúdo de cada posição que pode ser reciclada
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    // Prepara um item que será reciclado
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: TextItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Tarefa) {
            binding.tarefa = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TextItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}