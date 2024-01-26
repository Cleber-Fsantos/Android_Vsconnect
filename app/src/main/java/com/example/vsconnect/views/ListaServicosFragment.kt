package com.example.vsconnect.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vsconnect.apis.EndpointInterface
import com.example.vsconnect.apis.retrofitConfig
import com.example.vsconnect.databinding.FragmentListaServicosBinding
import com.example.vsconnect.models.Servico
import retrofit2.*

class ListaServicosFragment : Fragment() {

    private val clienteRetrofit = retrofitConfig.obterInstanciaRetrofit()

    private val endpoints = clienteRetrofit.create(EndpointInterface::class.java)

    private var _binding: FragmentListaServicosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListaServicosBinding.inflate(inflater, container, false)
        val root: View = binding.root


        endpoints.listarServicos().enqueue(object : Callback<List<Servico>> {
            override fun onResponse(call: Call<List<Servico>>, response: Response<List<Servico>>) {
                println(response.body())
            }

            override fun onFailure(call: Call<List<Servico>>, t: Throwable) {
                println("Falha na Requisição: ${t.message}")
            }

        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}