package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val statements = arrayListOf<QuizStatement>()
    private val statementAdapter = QuizStatementAdapter(statements)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.rvStatements.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.rvStatements.adapter = statementAdapter

        // Set values from data model in list
        for ((key, value) in QuizStatement.QUIZ_STATEMENTS) {
            statements.add(QuizStatement(key, value))
        }
        statementAdapter.notifyDataSetChanged()

        binding.rvStatements.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvStatements)
    }

    private fun checkAnswer(answerPosition: Int, givenAnswer: Boolean): Boolean {
        return statements[answerPosition].quizAnswer == givenAnswer
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition

                if (direction == ItemTouchHelper.LEFT) {
                    // Left swipe answers false
                    if (checkAnswer(position, false)) {
                        statements.removeAt(position)
                        statementAdapter.notifyDataSetChanged()
                    } else {
                        Snackbar.make(rvStatements, "Incorrect answer", Snackbar.LENGTH_SHORT).show()
                    }
                } else if (direction == ItemTouchHelper.RIGHT) {
                    // Right swipe answers true
                    if (checkAnswer(position, true)) {
                        statements.removeAt(position)
                        statementAdapter.notifyDataSetChanged()
                    } else {
                        Snackbar.make(rvStatements, "Incorrect answer", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return ItemTouchHelper(callback)
    }
}