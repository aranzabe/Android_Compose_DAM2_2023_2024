package com.example.mvvm_room.Listado

import Modelo.Usuario
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mvvm_room.Listado.data.UserDataBase
import android.content.Context
import android.service.autofill.UserData
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.mvvm_room.Listado.data.UserDao
import com.example.mvvm_room.Listado.data.UsuarioEntity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListadoViewModel(private val dao:UserDao) : ViewModel() {
    //private val _usuarios : ArrayList<UsuarioEntity> by mutableStateOf(arrayListOf())
    var usuarios : ArrayList<UsuarioEntity> by mutableStateOf(arrayListOf())

    init {
        viewModelScope.launch {
            dao.getUsers().collectLatest {
                usuarios = it
            }
        }
    }

    fun onUserCreated(us: UsuarioEntity, context: Context) = viewModelScope.launch{
        dao.addUser(us)
    }

    /*
    _usuarios.add(us)
        viewModelScope.launch {
            dao.getUsers().collectLatest {
                _usuarios = it
            }
        }
     */

    fun onItemRemove(u: Usuario) {

    }
}