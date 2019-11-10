package com.example.hello.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hello.databinding.TextItemViewBinding

class Adapter(val clickListener: TarefaListener) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    // Lista de itens que vão aparecer na tela
    var listaTarefas = mutableListOf<Tarefa>()
        // Notifica quando há alterações nos itens
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    // Tamanho total de itens da lista apresentados na tela
    override fun getItemCount() = listaTarefas.size

    // Preenche o conteúdo de cada posição que pode ser reciclada
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listaTarefas[position]
        holder.bind(item, clickListener)
    }

    // Prepara um item que será reciclado
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: TextItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Tarefa,
            clickListener: TarefaListener
        ) {
            // Passa variáveis para o data binding no layout
            binding.tarefa = item
            binding.clickListener = clickListener
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

class TarefaListener(val clickListener: (tarefa: Tarefa) -> Unit) {
    fun onClick(tarefa: Tarefa) = clickListener(tarefa)
}