package com.basset.pillease.activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.basset.pillease.R
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class EditScheduleActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var timePicker: EditText
    private lateinit var currentTimeDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_schedule)

        firestore = FirebaseFirestore.getInstance()
        timePicker = findViewById(R.id.timePicker)
        currentTimeDisplay = findViewById(R.id.currentTimeDisplay)
        val btnSaveTime = findViewById<Button>(R.id.btnSaveTime)

        loadCurrentMedicationTime()

        btnSaveTime.setOnClickListener {
            val time = timePicker.text.toString()
            if (time.isNotEmpty()) {
                saveMedicationTimeToFirestore(time)
            }
        }
    }

    private fun loadCurrentMedicationTime() {
        firestore.collection("schedules").document("scheduleTime")
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val currentTime = document.getString("time")
                    currentTimeDisplay.text = "Heure de prise de médicaments: $currentTime"
                    timePicker.setText(currentTime)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Erreur lors du chargement de l'horaire", Toast.LENGTH_SHORT).show()
            }
    }

    private fun saveMedicationTimeToFirestore(time: String) {
        val scheduleData = hashMapOf("time" to time)
        firestore.collection("schedules")
            .document("scheduleTime")
            .set(scheduleData)
            .addOnSuccessListener {
                Toast.makeText(this, "Horaire enregistré avec succès", Toast.LENGTH_SHORT).show()
                currentTimeDisplay.text = "Heure de prise de médicaments: $time"
                scheduleAlarm(time, true)
                scheduleAlarm(time, false)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Erreur lors de l'enregistrement de l'horaire", Toast.LENGTH_SHORT).show()
            }
    }

    private fun scheduleAlarm(time: String, exactTime: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !canScheduleExactAlarm()) {
            requestScheduleExactAlarmPermission()
            return
        }

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(this, MedicationAlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val date = sdf.parse(time) ?: return
        val calendar = Calendar.getInstance().apply {
            timeInMillis = date.time
            if (!exactTime) add(Calendar.MINUTE, -5)
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmIntent)
    }

    private fun canScheduleExactAlarm(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            return alarmManager.canScheduleExactAlarms()
        }
        return true
    }

    private fun requestScheduleExactAlarmPermission() {
        val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
        startActivity(intent)
    }
}