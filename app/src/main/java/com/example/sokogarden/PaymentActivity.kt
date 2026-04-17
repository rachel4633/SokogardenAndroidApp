package com.example.sokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Find views by use if their id's
        val txtname = findViewById<TextView>(R.id.txtProductName)
        val textcost = findViewById<TextView>(R.id.txtProductCost)
        val imgProduct = findViewById<ImageView>(R.id.imgProduct)
        val txtdescription =findViewById<TextView>(R.id.txtdescription)

//        Retrieve the data passed from the previous Activity
        val name = intent.getStringExtra("product_name")
        val cost = intent.getIntExtra("product_cost",0)
        val description = intent.getStringExtra("product_description")
        val product_photo = intent.getStringExtra("product_photo")

//        update the text views with the data passed from the previous activity

        txtname.text = name
        textcost.text = "Ksh $cost"
        txtdescription.text = description


//        specify the img url

        val imageUrl = "https://nkiroterakel.alwaysdata.net/static/images/$product_photo"

        Glide.with(this)
            .load(imageUrl )
            .placeholder(R.drawable.ic_launcher_background) // Make sure you have a placeholder image
            .into(imgProduct)

//        find the Edittext and the Pay Now button by use of their id's
        val phone = findViewById<EditText>(R.id.phone)
        val btnpay = findViewById<Button>(R.id.pay)

//        set click listener on the button
        btnpay.setOnClickListener {
            val api = "https://nkiroterakel.alwaysdata.net/api/mpesa_payment"

//            create a requestParams
            val data = RequestParams()

//            insert data into the RequestParams
            data.put("amount" ,cost)
            data.put("phone", phone.text.toString().trim())
            data.put("description",description)


//            import the helper class
            val helper = ApiHelper(applicationContext)

//            access the post function inside of the helper class

            helper.post(api, data)

//            clear the phone number from the edit text
            phone.text.clear()

        }

    }
}