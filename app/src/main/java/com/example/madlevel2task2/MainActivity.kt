package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding

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
    }
}