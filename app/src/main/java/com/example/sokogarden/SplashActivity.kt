package com.example.sokogarden

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val typingTextView = findViewById<TextView>(R.id.typingTextView)
        val message = "Welcome to SokoGarden Application"

        animateTextTypewriter(typingTextView, message)

        // Switch to the next screen after 4 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            // Replace MainActivity::class.java with your actual home screen class name
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 4000)
    }

    private fun animateTextTypewriter(textView: TextView, text: String) {
        val delay: Long = 100
        val handler = Handler(Looper.getMainLooper())
        for (i in text.indices) {
            handler.postDelayed({
                textView.text = text.substring(0, i + 1)
            }, delay * i)
        }
    }
}