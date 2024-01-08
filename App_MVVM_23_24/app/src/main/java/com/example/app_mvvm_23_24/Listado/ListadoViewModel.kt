package com.example.app_mvvm_23_24.Listado

import Modelo.Usuario
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListadoViewModel : ViewModel() {
    private val _usuarios : ArrayList<Usuario> by mutableStateOf(arrayListOf())
    val usuarios : ArrayList<Usuario> = _usuarios

    private val _showDialogBorrar = MutableLiveData<Boolean>()
    val showDialogBorrar: LiveData<Boolean> = _showDialogBorrar

    lateinit var usuBorrar:Usuario

    fun dialogClose() {
        _showDialogBorrar.value = false
    }

    fun dialogOpen() {
        _showDialogBorrar.value = true
    }

    fun usuarioBorrar(u:Usuario){
        //_usuBorrar.value = u
        usuBorrar = u
    }

    fun onUserCreated(us : Usuario) {
        _usuarios.add(us)
    }

    fun onItemRemove(u: Usuario) {
        _usuarios.apply {
            remove(u)
        }
    }
}