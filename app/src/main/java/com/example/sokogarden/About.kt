package com.example.sokogarden

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale


class About : AppCompatActivity() {

    //    declare a variable that will hold the text  to speech object
    lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        find the textview and the button by use of their id's
        val textview = findViewById<TextView>(R.id.aboutTxt)
        val speechbutton = findViewById<Button>(R.id.btnListen)

        tts = TextToSpeech(this) {
            if (it == TextToSpeech.SUCCESS) {
                val locale = null
                tts.language = Locale.US

                // the voice code
                val voices = tts.voices
                for (voice in voices){
                    // voice for male or female
                    if (voice.name.contains("female", ignoreCase = true) && voice.locale == Locale.US){
                        tts.setVoice(voice)
                        break
                    }
                }

            }//end
            speechbutton.setOnClickListener {
                val text = textview.text.toString()
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)

            }
        }

    }

    override fun onDestroy() {
        if (::tts.isInitialized){
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }

}