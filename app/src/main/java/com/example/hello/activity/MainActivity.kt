package com.example.hello.activity

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.hello.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    var adapter: Adapter? = null

    // Cria e prepara a tela e seu conteúdo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            incluirTarefa()
        }

        // Ao criar o adapter precisa dar o comportamento da função de clique no item da lista
        adapter = Adapter(TarefaListener { tarefa ->
            // Implementa o que acontece ao clicar no item da lista
            // Remove o item da lista
            adapter!!.listaTarefas.remove(tarefa)
            adapter!!.notifyDataSetChanged()
            // Confirma a remoção na tela
            Snackbar.make(
                coordinator,
                "Tarefa \"%s\" foi concluída".format(tarefa.nome),
                Snackbar.LENGTH_LONG
            ).show()
        })
        list.adapter = adapter
        // Operador de validação de não nulo que só executa se a variável não for nula
        adapter!!.listaTarefas = mutableListOf(Tarefa("Tarefa 01"))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Cria dialog para inclusão de tarefas
    fun incluirTarefa() {
        // Inicializa o dialog (caixinha que insere o texto)
        val builder = AlertDialog.Builder(this)
        // Define o título da caixinha
        builder.setTitle("Nova Tarefa")

        // Usa o `inflater` para gerar o campo de texto que é incluído na caixinha
        val view = layoutInflater.inflate(R.layout.dialog_input, null)
        // Pega apenas a caixa de texto do layout que é incluído na caixinha
        val tarefaInput = view.findViewById(R.id.tarefaInput) as EditText
        // Coloca o layout do campo de texto na caixinha
        builder.setView(view)

        // Inlui um comportamento para o botão de "OK" da caixinha
        builder.setPositiveButton("Incluir") { _, _ ->
            // Captura o texto inserido na caixinha ao confirmar o botão dela
            val nomeTarefa = tarefaInput.text.toString()
            // Valida que texto não é vazio
            if (!nomeTarefa.isBlank()) {

                // Inclui uma nova terefa na lista que está no adapter
                // Validação de não nulo para o adapter
                adapter!!.listaTarefas.plusAssign(Tarefa(nomeTarefa))
                adapter!!.notifyDataSetChanged()
                // Confirma a inclusão na tela
                Snackbar.make(
                    coordinator,
                    "Tarefa \"%s\" foi incluída".format(nomeTarefa),
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                // Informa que não inclui na tela
                Snackbar.make(coordinator, "Nenhuma tarefa foi incluída", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }

        builder.show()
    }
}
