package com.example.otroscomponentes

import Pantalla2.ui.Pantalla2
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.otroscomponentes.ui.theme.OtrosComponentesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtrosComponentesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    var selectedItemMiOpcion by remember { mutableStateOf("Principal") }

                    NavHost(navController = navController, startDestination = Rutas.Pantalla1) {
                        composable(Rutas.Pantalla1) { MiScaffold(navController,selectedItemMiOpcion){
                            selectedItemMiOpcion = it
                        } }
                        composable(Rutas.Pantalla2) { Pantalla2(navController) }
                    }
                }
            }
        }
    }
}
