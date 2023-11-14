package com.example.cards

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cards.ui.theme.CardsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column{
                        miCard()
                        miBadgeBox()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun miCard(){
    Card(modifier = Modifier
        //.size(50.dp)
        .padding(20.dp)
        .fillMaxWidth() // Ocupa todo el ancho disponible
        .height(100.dp)
        ,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(20.dp),
        //shape =MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        border = BorderStroke(5.dp, Color.DarkGray),
        onClick = {
                  Log.e("Fernando","Pulsado el contenido del Card")
        },
        ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(text = "Texto 1")
            Text(text = "Texto 2")
            Text(text = "Texto 3")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun miBadgeBox(){
    // Prueba quitando y poniendo todos los colores.
    BadgedBox(modifier = Modifier.padding(15.dp),  badge = { Badge() { Text("80") } }) {
//    BadgedBox(modifier = Modifier.padding(15.dp).background(color = Color.Cyan),  badge = { Badge(Modifier.background(Color.Green), containerColor = Color.Red) { Text("80") } }) {
        Icon(
            Icons.Filled.MailOutline,
            contentDescription = "Favorite"
        )
    }
}