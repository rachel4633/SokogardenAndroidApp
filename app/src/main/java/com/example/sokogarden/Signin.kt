package com.example.sokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams
import org.w3c.dom.Text

class Signin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        find the 2 edit text, a button and a textview by ise of their ids
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val signinButton = findViewById<Button>(R.id.signinbtn)
        val signupTextview = findViewById<TextView>(R.id.signuptxt)

   // On the textView set on click listener such that when clicked it navigates you to the signup Page
        signupTextview.setOnClickListener {
            val intent  = Intent(applicationContext, Signup::class.java)
            startActivity(intent)
        }
        signinButton.setOnClickListener {
//            specify the api
            val api = "https://nkiroterakel.alwaysdata.net/api/signin"
//          create a RequestParams that will enable you to hold the data in form of a bundle/package
            val data = RequestParams()
//            add/append/attach the email and the password
            data.put("email",email.text.toString())
            data.put("password",password.text.toString())
//            import api helper
            val helper = ApiHelper(applicationContext)
//            by use of the function post_login inside the helper class ,post your data
         helper.post_login(api, data)
        }

        }

    }
