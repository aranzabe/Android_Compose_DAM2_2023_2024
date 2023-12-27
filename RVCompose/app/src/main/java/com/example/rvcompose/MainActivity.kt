package com.example.rvcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rvcompose.ui.theme.GridUsuarios
import com.example.rvcompose.ui.theme.RVComposeTheme
import com.example.rvcompose.ui.theme.RVUsuariosCols
import com.example.rvcompose.ui.theme.RVUsuariosControlsView
import com.example.rvcompose.ui.theme.RVUsuariosFilas
import com.example.rvcompose.ui.theme.RVUsuariosSticky
import com.example.rvcompose.ui.theme.simpleRecyclerView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RVComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //simpleRecyclerView()
                    RVUsuariosFilas()
//                    RVUsuariosCols()
//                    GridUsuarios()
//                    RVUsuariosControlsView()
                    RVUsuariosSticky()
                }
            }
        }
    }
}

