package com.basset.pillease.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.basset.pillease.R
import com.google.firebase.auth.FirebaseAuth // Importez ceci si vous utilisez Firebase Authentication

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnManageMedication = findViewById<Button>(R.id.btnManageMedication)
        btnManageMedication.setOnClickListener {
            val intent = Intent(this, MedicationManagementActivity::class.java)
            startActivity(intent)
        }

        val btnEditSchedule = findViewById<Button>(R.id.editehoraire)
        btnEditSchedule.setOnClickListener {
            val intent = Intent(this, EditScheduleActivity::class.java)
            startActivity(intent)
        }


        val logoutButton = findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut() // faut que je configure frbse... c un rapp
            val loginIntent = Intent(this, AuthentificationActivity::class.java)
            startActivity(loginIntent)
            finish()
        }
        val btnVirtualAssistant = findViewById<Button>(R.id.btnVirtualAssistant)
        btnVirtualAssistant.setOnClickListener {
            val intent = Intent(this, VirtualAssistantActivity::class.java)
            startActivity(intent)
        }

    }
}

