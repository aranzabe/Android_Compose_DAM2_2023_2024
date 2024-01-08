package com.example.app_mvvm_aws.Listado

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Usuario

class ListadoViewModel : ViewModel() {
    val TAG = "Fernando"
    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _usuBorrar = MutableLiveData<Usuario?>()
    val usuBorrar: LiveData<Usuario?> = _usuBorrar
//    lateinit var usuBorrar:Usuario

    private val _usuarios : ArrayList<Usuario> by mutableStateOf(arrayListOf())
    val usuarios : ArrayList<Usuario> = _usuarios

    fun dialogClose() {
        _showDialog.value = false
    }

    fun dialogOpen() {
        _showDialog.value = true
    }

    fun usuarioBorrar(u:Usuario){
        _usuBorrar.value = u
//        usuBorrar = u
    }

    fun onUserCreated(us: Usuario?) {
        if (us != null) {
            _usuarios.add(us)
        }

        if (us != null) {
            Amplify.DataStore.save(
                us,
                { success -> Log.i(TAG, "Saved item: " + success.item().toString()) },
                { error -> Log.e(TAG, "Could not save item to DataStore", error) }
            )
        }
    }

    fun getUsers(){
        usuarios.clear()
        Amplify.DataStore.query(
            Usuario::class.java,
            { items ->
                while (items.hasNext()) {
                    val item = items.next()
                    Log.i(TAG, "Queried item: ${item.toString()}")
                    usuarios.add(item)
                }
            },
            { failure -> Log.e(TAG, "Could not query DataStore", failure) }
        )
    }

    fun onItemRemove(toDeleteItem: Usuario) {
        Amplify.DataStore.delete(toDeleteItem,
            { deleted -> Log.i(TAG, "Deleted item.") },
            { failure -> Log.e(TAG, "Delete failed.", failure) }
        )
        usuarios.apply {
            remove(toDeleteItem)
        }
    }
}

