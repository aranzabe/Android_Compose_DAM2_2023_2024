package com.example.appmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appmvvm.Listado.Listado
import com.example.appmvvm.Listado.ListadoViewModel
import com.example.appmvvm.Principal.Login
import com.example.appmvvm.Principal.PrincipalViewModel
import com.example.appmvvm.ui.theme.AppMVVMTheme

class MainActivity : ComponentActivity() {
    //private val tasksViewModel:TasksViewModel by viewModels()

    val pvm = PrincipalViewModel()
    val lvm = ListadoViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppMVVMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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

