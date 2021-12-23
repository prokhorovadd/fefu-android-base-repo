package ru.fefu.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.fefu.activity.R

import ru.fefu.activity.databinding.LoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarLogin.setOnClickListener {
            finish()
        }
    }
}