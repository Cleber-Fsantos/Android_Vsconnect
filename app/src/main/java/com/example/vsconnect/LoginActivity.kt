package com.example.vsconnect

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.vsconnect.apis.EndpointInterface
import com.example.vsconnect.apis.RetrofitConfig
import com.example.vsconnect.databinding.ActivityLoginBinding
import com.example.vsconnect.models.Login
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.charset.StandardCharsets
import java.util.Base64

class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding

    private val clienteRetrofit = RetrofitConfig.obterInstanciaRetrofit()

    private val  endpoints = clienteRetrofit.create(EndpointInterface::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //com binding conseguimos receber informações do Layout
        binding = ActivityLoginBinding.inflate(layoutInflater)

        val sharedPreferences = getSharedPreferences("idUsuario", Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        editor.remove("idUsuario")
        editor.apply()

        //Função Ouvinte de Click -> apos clicar tudo sera executado
        binding.btnEntrar.setOnClickListener {
            //variavel mainIntent com a intenção de sair da LoginActivity e ir para MainActivity
            //Redirecionamento de forma manual
            //val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
            //startActivity(mainIntent)
            //finish()
            autenticarUsuario()
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

        endpoints.login(usuario).enqueue(object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                when(response.code()){
                    200 -> {
                        val idUsuario = decodificarToken(response.body().toString())

                        val sharedPreferences = getSharedPreferences("idUsuario",Context.MODE_PRIVATE)

                        val editor = sharedPreferences.edit()

                        editor.putString("idUsuario", idUsuario.toString())

                        editor.apply()

                        val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
                    }
                    403 -> { tratarFalhaNaAuntenticacao("Email e/ou senha inválidos") }
                    else -> { tratarFalhaNaAuntenticacao("Falha ao se logar") }
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                tratarFalhaNaAuntenticacao("Falha ao tentar se logar!")
            }

        })

    }

    private  fun tratarFalhaNaAuntenticacao(mensagemErro: String){
        Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT).show()
    }

    private fun decodificarToken(token: String): Any {
        val partes = token.split(".")
        val payloadBase64 = partes[1]

        val payloadBytes = Base64.getUrlDecoder().decode(payloadBase64)
        val payloadJson = String(payloadBytes, StandardCharsets.UTF_8)

        val json = JSONObject(payloadJson)
        return json["idUsuario"].toString()
    }
}