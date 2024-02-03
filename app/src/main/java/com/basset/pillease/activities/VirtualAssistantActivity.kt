package com.basset.pillease.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.basset.pillease.R

class VirtualAssistantActivity : AppCompatActivity() {

    private lateinit var medicationInfoDatabase: MedicationInfoDatabase
    private lateinit var questionSpinner: Spinner
    private lateinit var answerTextView: TextView
    private lateinit var askButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_virtual_assistant)

        medicationInfoDatabase = MedicationInfoDatabase()
        questionSpinner = findViewById(R.id.questionSpinner)
        answerTextView = findViewById(R.id.answerTextView)
        askButton = findViewById(R.id.askButton)

        val questionsAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            medicationInfoDatabase.getAllQuestions()
        )
        questionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        questionSpinner.adapter = questionsAdapter

        askButton.setOnClickListener {
            val selectedQuestion = questionSpinner.selectedItem.toString()
            val answer = medicationInfoDatabase.getResponse(selectedQuestion)
            answerTextView.text = answer
        }
    }
}