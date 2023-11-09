package com.example.imagenes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Face3
import androidx.compose.material.icons.rounded.Face6
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imagenes.ui.theme.ImagenesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImagenesTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Greeting() {
    var context = LocalContext.current
    var imagenVisible by remember { mutableStateOf(true) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .horizontalScroll(
                rememberScrollState()

            )
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (imagenVisible) {
                Image(
                    painter = painterResource(id = R.drawable.ic_cafetera),
                    contentDescription = "Ejemplo de imagen",
                    alpha = 0.5f
                    //modifier = Modifier.padding(top = 20.dp, start = 175.dp)
                )
            }
        }
        Button(onClick = {
            imagenVisible = !imagenVisible
        }) {
            Text(text = if (imagenVisible) "Ocultar imagen" else "Mostrar Imagen")
        }


        Spacer(modifier = Modifier.height(100.dp))
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
            Image(
                painter = painterResource(id = R.drawable.ic_cafetera2),
                contentDescription = "Ejemplo de imagen",
                alpha = 0.5f,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.background)
                    .border(1.dp, Color.Cyan, CircleShape)
                    .clickable {
                        Toast
                            .makeText(context, "Hola, has pulsado la imagen", Toast.LENGTH_SHORT)
                            .show()
                    }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        //Iconos podemos encontrar en: https://fonts.google.com/icons
        //Para incluir todos los de la página nos hará falta implementar en Graddle: implementation("androidx.compose.material:material-icons-extended:1.5.3")
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
            Icon(
                imageVector = Icons.Rounded.Face3,
                contentDescription = "Icono",
                tint = Color.Blue,
                modifier = Modifier.size(50.dp)
            )
        }

    }

}

