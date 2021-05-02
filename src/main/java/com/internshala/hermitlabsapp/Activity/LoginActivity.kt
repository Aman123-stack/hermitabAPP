package com.internshala.hermitlabsapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.internshala.hermitlabsapp.R
import com.internshala.hermitlabsapp.Fragment.RegisterPage

class LoginActivity : AppCompatActivity() {
    lateinit var loginImage: ImageView
    lateinit var cardView: CardView
    lateinit var txtForgotPassword: TextView
    lateinit var btnlogin: Button
    lateinit var txtSignUp: TextView
    lateinit var txtInEmail: TextInputEditText
    lateinit var txtInPassword: TextInputEditText
    lateinit var txtInConfirmPass: TextInputEditText
    val validEmail="user@gmail.com"
    val validPassword="aman"
    val validConfirmPassword="aman"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginImage = findViewById(R.id.loginImage)
        cardView = findViewById(R.id.cardView)
        txtForgotPassword = findViewById(R.id.txtForgotPassword)
        txtInEmail = findViewById(R.id.txtInEmail)
        txtInPassword = findViewById(R.id.txtInPassword)
        txtInConfirmPass = findViewById(R.id.txtInConfirmPass)
        btnlogin = findViewById(R.id.btnLogIn)
        txtSignUp = findViewById(R.id.txtSignUp)
        btnlogin.setOnClickListener {
            val emailText = txtInEmail.text.toString()
            val passwordText = txtInPassword.text.toString()
            val confirmPassText = txtInConfirmPass.text.toString()
            if ((emailText == validEmail) && (passwordText == validPassword) && (confirmPassText == validConfirmPassword)) {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this@LoginActivity, "Incorrect Credential", Toast.LENGTH_SHORT)
                    .show()
            }
            txtSignUp.setOnClickListener {
                val signUp = Intent(this@LoginActivity, RegisterPage::class.java)
                startActivity(signUp)
            }
        }
    }
}

