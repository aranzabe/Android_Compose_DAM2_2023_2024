package com.example.ejemplomvvm.Login.ui.ViewModel

import Modelo.Usuario
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Se hace un viewModel por "ventana" para gestionar todos sus controles y estados. En este caso hemos puesto también la lista de usuarios (ya no lo haremos con una clase Object).
 * Esta lista de usuarios puede estar aquí (en cuyo caso tenemos que pasarla también a la ventana2) o podemos hacer un viewModel solo para gestionar esta información que tendríamos que pasar a todas las ventanas que necesiten esta información.
 */
class LoginViewModel : ViewModel() {

    private val _email = MutableLiveData<String>() //Este pertenece a esta clase
    val email : LiveData<String> = _email          //Atributo que usamos para la suscripción

    private val _password = MutableLiveData<String>() //Este pertenece a esta clase
    val password : LiveData<String> = _password          //Atributo que usamos para la suscripción

    private val _check = MutableLiveData<Boolean>() //Este pertenece a esta clase
    val estadoCheck : LiveData<Boolean> = _check         //Atributo que usamos para la suscripción


    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable : LiveData<Boolean> = _isLoginEnable

    private val _usuarios : ArrayList<Usuario> by mutableStateOf(arrayListOf())
    val usuarios : ArrayList<Usuario> = _usuarios


    fun onLoginCambiado(email:String){
        _email.value = email
    }

    fun onPassCambiado(pass:String){
        _password.value = pass
    }

    fun onRegistroCambiado(email:String, pass:String){
        _email.value = email
        _password.value = pass
        _isLoginEnable.value = enableLogin(email,pass)
    }

    fun onCheckCambiado(ch:Boolean){
        _check.value = ch
    }

    fun enableLogin(email:String, pass:String):Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && pass.length > 6
    }

    fun addUsuario(us : Usuario){
        this._usuarios.add(us)
    }
}