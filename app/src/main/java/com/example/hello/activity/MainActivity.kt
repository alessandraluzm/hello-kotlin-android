package com.example.hello.activity

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.hello.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    var adapter = Adapter()

    // Cria e prepara a tela e seu conteúdo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            incluirTarefa()
        }

        list.adapter = adapter
        adapter.data = mutableListOf(Tarefa("Tarefa 01"))
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
        // Inicializa o dialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Nova Tarefa")

        // Cria o campo de texto que é usado no dialog
        val view = layoutInflater.inflate(R.layout.dialog_input, null)
        val tarefaInput = view.findViewById(R.id.tarefaInput) as EditText
        builder.setView(view)

        // Cria a interação de incluir a nova tarefa
        builder.setPositiveButton(android.R.string.ok) { _, _ ->
            val nomeTarefa = tarefaInput.text.toString()
            if (!nomeTarefa.isBlank()) {
                adapter.data.plusAssign(Tarefa(nomeTarefa))
                Snackbar.make(coordinator, "Tarefa \"%s\" foi incluída".format(nomeTarefa), Snackbar.LENGTH_LONG).show()
            } else {
                Snackbar.make(coordinator, "Nenhuma tarefa foi incluída", Snackbar.LENGTH_SHORT).show()
            }
        }

        builder.show()
    }
}
