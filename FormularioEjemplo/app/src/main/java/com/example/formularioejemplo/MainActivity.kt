package com.example.formularioejemplo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.formularioejemplo.Ventana2.ui.Pant2
import com.example.formularioejemplo.login.ui.Login
import com.example.formularioejemplo.ui.theme.FormularioEjemploTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormularioEjemploTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Rutas.Login){
                        composable(Rutas.Login){
                            Login(navController)
                        }
                        composable(Rutas.Pantalla2){
                            Pant2(navController)
                        }
                        }
                    }
                }
            }
        }
    }


