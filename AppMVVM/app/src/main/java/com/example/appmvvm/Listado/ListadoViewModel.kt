package com.example.appmvvm.Listado

import Modelo.Usuario
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ListadoViewModel : ViewModel() {
    private val _usuarios : ArrayList<Usuario> by mutableStateOf(arrayListOf())
    val usuarios : ArrayList<Usuario> = _usuarios

    fun onUserCreated(us : Usuario) {
        _usuarios.add(us)
    }

    fun onItemRemove(u: Usuario) {

    }
}