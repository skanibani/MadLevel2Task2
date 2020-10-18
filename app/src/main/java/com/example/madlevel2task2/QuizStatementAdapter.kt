package com.example.madlevel2task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.StatementItemBinding

class QuizStatementAdapter(private val quizStatements: List<QuizStatement>): RecyclerView.Adapter<QuizStatementAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = StatementItemBinding.bind(itemView)

        fun databind(quizStatement: QuizStatement) {
            binding.textView.text = quizStatement.quizStatementText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.statement_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(quizStatements[position])
    }

    override fun getItemCount(): Int {
        return quizStatements.size
    }
}