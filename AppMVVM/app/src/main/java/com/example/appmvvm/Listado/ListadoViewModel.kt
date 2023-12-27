package com.example.appmvvm.Listado

import Modelo.Usuario
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListadoViewModel : ViewModel() {
    private val _usuarios : ArrayList<Usuario> by mutableStateOf(arrayListOf())
    val usuarios : ArrayList<Usuario> = _usuarios

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    lateinit var usuBorrar:Usuario

    fun dialogClose() {
        _showDialog.value = false
    }

    fun dialogOpen() {
        _showDialog.value = true
    }

    fun usuarioBorrar(u:Usuario){
        //_usuBorrar.value = u
        usuBorrar = u
    }

    fun onUserCreated(us : Usuario) {
        _usuarios.add(us)
    }

    fun onItemRemove(u: Usuario) {
        usuarios.apply {
            remove(u)
        }
    }
}