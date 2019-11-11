package com.example.hello.activity

import android.content.Context


object Persistencia {

    // Constante que dá nome ao "cofre" onde ficam armazenadas as informações
    val SHARED_PREFERENCES: String = "SHARED_PREFERENCES"

    /**
     * Armazena uma lista de tarefas.
     *
     * @param identificador: nome da lista (identifica o que vai guardar)
     * @param lista: lista e seu conteúdo
     */
    fun salvarTarefas(identificador: String, lista: MutableList<Tarefa>, context: Context) {
        val prefs = context.getSharedPreferences(SHARED_PREFERENCES, 0)
        val editor = prefs.edit()
        // Armazena o tamanho da lista para saber carregar quantidade depois
        editor.putInt(identificador + "_size", lista.size)
        for (i in lista.indices)
            // Armazena apenas os nomes das tarefas
            editor.putString(identificador + "_" + i, lista[i].nome)
        editor.apply()
    }

    /**
     * Carrega uma lista de tarefas.
     *
     * @param identificador: nome da lista
     */
    fun carregarTarefas(identificador: String, context: Context): MutableList<Tarefa> {
        val prefs = context.getSharedPreferences(SHARED_PREFERENCES, 0)
        val tamanho = prefs.getInt(identificador + "_size", 0)
        val lista = mutableListOf<Tarefa>()
        for (i in 0 until tamanho) {
            // Cria as tarefas a partir do seus nomes que foram gravados
            val nome = prefs.getString(identificador + "_" + i, null)
            lista.plusAssign(Tarefa(nome!!))
        }
        return lista
    }
}