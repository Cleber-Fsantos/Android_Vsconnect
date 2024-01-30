package com.example.vsconnect.apis

import com.example.vsconnect.models.Login
import com.example.vsconnect.models.Servico
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface EndpointInterface {
    @GET ("servicos")
    fun listarServicos(): Call<List<Servico>>

    @POST("login")
    fun login(@Body usuario: Login): Call<JsonObject>

    @GET("usuarios/{idUsuario}")
    fun buscarUsuarioPorID(@Path(value = "idUsuario", encoded = true) idUsuario: UUID): Call<JsonObject>
}