package com.example.vsconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vsconnect.databinding.ActivityLoginBinding
class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //com binding conseguimos receber informações do Layout
        binding = ActivityLoginBinding.inflate(layoutInflater)
        //Função Ouvinte de Click -> apos clicar tudo sera executado
        binding.btnEntrar.setOnClickListener {
            //variavel mainIntent com a intenção de sair da LoginActivity e ir para MainActivity
            val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)

            startActivity(mainIntent)

            finish()
        }
        setContentView(binding.root)
    }
}