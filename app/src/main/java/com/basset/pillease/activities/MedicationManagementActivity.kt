package com.basset.pillease.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.basset.pillease.R

class MedicationManagementActivity : AppCompatActivity() {

    private lateinit var medicationNameEditText: EditText
    private lateinit var medicationQuantityEditText: EditText
    private lateinit var addMedicationButton: Button
    private lateinit var medicationListView: ListView

    private var medicationList = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medication_management)

        // Initialisation des composants de l'interface utilisateur
        medicationNameEditText = findViewById(R.id.medicationName)
        medicationQuantityEditText = findViewById(R.id.medicationQuantity)
        addMedicationButton = findViewById(R.id.addMedicationButton)
        medicationListView = findViewById(R.id.medicationList)

        // Initialisation de l'adaptateur pour la ListView
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, medicationList)
        medicationListView.adapter = adapter

        // Définition du comportement du bouton d'ajout
        addMedicationButton.setOnClickListener {
            val name = medicationNameEditText.text.toString()
            val quantity = medicationQuantityEditText.text.toString()
            if (name.isNotEmpty() && quantity.isNotEmpty()) {
                val medicationInfo = "$name - $quantity"
                medicationList.add(medicationInfo)
                adapter.notifyDataSetChanged() // Mettre à jour la ListView

                // Réinitialiser les champs de saisie
                medicationNameEditText.text.clear()
                medicationQuantityEditText.text.clear()
            }
        }
    }
}
