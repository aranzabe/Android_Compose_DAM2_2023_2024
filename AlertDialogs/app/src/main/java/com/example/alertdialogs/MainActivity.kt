package com.example.alertdialogs

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.alertdialogs.ui.theme.AlertDialogsTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlertDialogsTheme {
                var context = LocalContext.current

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Opción A --> Diálogo básico.
//                    var mostrarDialogo by remember { mutableStateOf(false) }
//                    Column {
//                        Button(onClick = {
//                            mostrarDialogo = true
//                        }) {
//                            Text(text = "Diálogo básico")
//                        }
//
//                        if (mostrarDialogo) {
//                            val elec = DialogoBasico()
//                            if (elec != -1) {
//                                Toast.makeText(context, "Opción elegida: $elec", Toast.LENGTH_SHORT)
//                                    .show()
//                            }
//                        }
//                    }

                    //Opción B --> Diálogo personalizado.
                    var mostrarDialogo by remember { mutableStateOf(false) }
                    Column {
                        Button(onClick = {
                            mostrarDialogo = true
                        }) {
                            Text(text = "Diálogo personalizado")
                        }

                        if (mostrarDialogo) {
                            //DialogoPersonalizado()
                            DialogoPersonalizadoAvanzado()
                        }
                    }
                }
            }
        }
    }
}

//-------------------------------------------------------------------------------
@Composable
fun DialogoBasico():Int {
    var mostrar by remember { mutableStateOf(true) }
    var eleccion by remember { mutableStateOf(-1) }
    if (mostrar) {
        AlertDialog(
            onDismissRequest = {
                // Esto se dispara si el usuario clickea fuera de la ventana.
                mostrar = false
            },
            title = {
                Text(text = "Título de la ventana")
            },
            text = {
                Text("¿Cómo estás Lorenzo?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        mostrar = false
                        eleccion = 1
                        Log.e("Fernando", "Te encuentras bien?")
                    }) {
                    Text("Genial")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        Log.e("Fernando", "Lo sabía...")
                        eleccion = 0
                        mostrar = false
                    }) {
                    Text("Bien j...")
                }
            }
        )
    }
    return eleccion
}

//-------------------------------------------------------------------------------
@Composable
fun DialogoPersonalizado(){
    var mostrar by remember { mutableStateOf(true)  }
    if (mostrar) {
            Dialog(onDismissRequest = { mostrar=false },
                   properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false) //Esto lo hace ventana de obligada lectura. Aunque pulse fuera.
            ){
                Column(modifier = Modifier
                    .height(150.dp)
                    .padding(10.dp)) {
                    Text(text = "Ejemplo de diálogo personalizado")
                    Button(
                        onClick = {
                            mostrar = false
                        }) {
                            Text("Salir")
                        }
                }
            }
    }
}


//-------------------------------------------------------------------------------
@Composable
fun DialogoPersonalizadoAvanzado(){
    var mostrar by remember { mutableStateOf(true)  }
    if (mostrar) {
        Dialog(onDismissRequest = { mostrar=false },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false) //Esto lo hace ventana de obligada lectura. Aunque pulse fuera.
        ){
            Column(modifier = Modifier
                .height(250.dp)
                .padding(10.dp)) {
                TituloDialogo(texto = "Elige la opción")
                itemsDialogo(email = "uno@gmail.com", draw = R.drawable.it1)
                itemsDialogo(email = "una@gmail.com", draw = R.drawable.it2)
                itemsDialogo(email = "Nuevo", draw = R.drawable.ic_add)
                Button(
                    onClick = {
                        mostrar = false
                    }) {
                    Text("Salir")
                }
            }
        }
    }
}
@Composable
fun TituloDialogo(texto:String){
    Text(text = texto, fontWeight = FontWeight.SemiBold, fontSize = 20.sp, modifier = Modifier.padding(12.dp))
}

@Composable
fun itemsDialogo(email:String, draw:Int){
    Row(verticalAlignment = Alignment.CenterVertically){
        Image(
            painter = painterResource(id = draw),
            contentDescription = ""
//            alpha = 0.5f
            //modifier = Modifier.padding(top = 20.dp, start = 175.dp)
        )
        Text(text = email, fontSize = 14.sp, color = Color.DarkGray, modifier = Modifier.padding(8.dp))
    }
}