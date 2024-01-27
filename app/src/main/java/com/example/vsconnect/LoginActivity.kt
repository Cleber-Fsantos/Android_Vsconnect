package com.example.vsconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.vsconnect.databinding.ActivityLoginBinding
import com.example.vsconnect.models.Login

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

    private fun autenticarUsuario(){
        val root: View = binding.root

        val idEmail = root.findViewById<EditText>(R.id.campo_email)
        val idSenha = root.findViewById<EditText>(R.id.campo_senha)

        val emailDigitado = idEmail.text.toString()
        val senhaDigitada = idSenha.text.toString()

        val usuario = Login(emailDigitado, senhaDigitada)
    }

}