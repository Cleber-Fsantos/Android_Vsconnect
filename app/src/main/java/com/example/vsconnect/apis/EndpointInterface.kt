package com.example.vsconnect.apis

import com.example.vsconnect.models.Servico
import retrofit2.Call
import retrofit2.http.GET

interface EndpointInterface {
    @GET ("servicos")
    fun listarServicos(): Call<List<Servico>>
}