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

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        find all views by ids
        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val phone = findViewById<EditText>(R.id.phone)
        val signupButton = findViewById<Button>(R.id.signupbtn)
        val signinTextView = findViewById<TextView>(R.id.signintxt)

//        below when a person clicks on the textview ,he/she is navigated to the signin page
        signinTextView.setOnClickListener {
            val intent = Intent(applicationContext,Signin::class.java)
            startActivity(intent)
        }
//        on click of the signup Button, we want to register a person
        signupButton.setOnClickListener {
//            specify the api endpoint
            val api = "https://nkiroterakel.alwaysdata.net/api/signup"
//            create a Request params - it is where we are going to hold all the data
            val data = RequestParams()

//            add/append the username ,email,password phone number
            data.put("username",username.text.toString().trim())
            data.put("email",email.text.toString().trim())
            data.put("password",password.text.toString().trim())
            data.put("phone",phone.text.toString().trim())

//            import the api helper class
            val helper = ApiHelper(applicationContext)

//            access the function class access the function post
            helper.post(api, data)




        }

    }
}