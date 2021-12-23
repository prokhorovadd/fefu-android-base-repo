package ru.fefu.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.fefu.activity.R

import ru.fefu.activity.databinding.RegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: RegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = RegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarRegistration.setOnClickListener {
            finish()
        }

        binding.btnReg2.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}