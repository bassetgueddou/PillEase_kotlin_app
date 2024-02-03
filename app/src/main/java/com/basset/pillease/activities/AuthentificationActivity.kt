package com.basset.pillease.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import com.basset.pillease.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import android.util.Patterns
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.widget.Toast

class AuthentificationActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var tvRegister: TextView
    private lateinit var textInputLayoutEmail: TextInputLayout
    private lateinit var textInputLayoutPassword: TextInputLayout
    private lateinit var btnConnect: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentification)
        auth = Firebase.auth
        tvRegister = findViewById(R.id.tvRegister)
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail)
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword)
        btnConnect = findViewById(R.id.btnConnect)

        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnConnect.setOnClickListener {
            if (validateEmail() && validatePassword()) {
                performLogin()
            }
        }
    }

    private fun validateEmail(): Boolean {
        val emailInput = textInputLayoutEmail.editText?.text.toString()
        return if (emailInput.isEmpty()) {
            textInputLayoutEmail.error = "Email cannot be empty"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputLayoutEmail.error = "Invalid email address"
            false
        } else {
            textInputLayoutEmail.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val passwordInput = textInputLayoutPassword.editText?.text.toString()
        return if (passwordInput.isEmpty()) {
            textInputLayoutPassword.error = "Password cannot be empty"
            false
        } else {
            textInputLayoutPassword.error = null
            true
        }
    }

    private fun performLogin() {
        val email = textInputLayoutEmail.editText?.text.toString()
        val password = textInputLayoutPassword.editText?.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {

                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
