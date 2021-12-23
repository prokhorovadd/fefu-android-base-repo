package ru.fefu.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity(R.layout.welcome) {

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)

        val button = findViewById<Button>(R.id.btnReg)
        val textView = findViewById<TextView>(R.id.third)

        button.setOnClickListener{
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        textView.setOnClickListener {
            val intent = Intent( this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
    }
}
