package com.example.mvvm_room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm_room.Listado.Listado
import com.example.mvvm_room.Listado.ListadoViewModel
import com.example.mvvm_room.Listado.data.UserDataBase
import com.example.mvvm_room.Principal.Login
import com.example.mvvm_room.Principal.PrincipalViewModel
import com.example.mvvm_room.ui.theme.MVVM_RoomTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVM_RoomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val db = Room.databaseBuilder(this, UserDataBase::class.java,"db_usuarios").build()
                    val dao = db.userDao()
                    val pvm = PrincipalViewModel()
                    val lvm = ListadoViewModel(dao)
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Rutas.Principal){
                        composable(Rutas.Principal){
                            Login(navController, pvm, lvm)
                        }
                        composable(Rutas.Listado){
                            Listado(navController, pvm, lvm)
                        }
                    }
                }
            }
        }
    }
}
