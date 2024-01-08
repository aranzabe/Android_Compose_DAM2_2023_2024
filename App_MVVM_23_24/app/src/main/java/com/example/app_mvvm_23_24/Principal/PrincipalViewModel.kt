package com.example.app_mvvm_23_24.Principal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PrincipalViewModel : ViewModel(){
    private val _nombre =  MutableLiveData<String>()
    val nombre : LiveData<String> = _nombre

    private val _edad =  MutableLiveData<Int>()
    val edad : LiveData<Int> = _edad

    private val _parte =  MutableLiveData<Boolean>()
    val parte : LiveData<Boolean> = _parte

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    fun onDialogClose() {
        this._nombre.value = ""
        this._edad.value = 0
        this._parte.value = false
        _showDialog.value = false
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun cambiaNombre(it: String) {
        this._nombre.value = it
    }

    fun cambiaEdad(e: Int) {
        this._edad.value = e
    }

    fun cambiaParte(it: Boolean) {
        this._parte.value = it
    }

}