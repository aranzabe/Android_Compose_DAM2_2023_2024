package com.example.texts

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.texts.ui.theme.TextsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //MiText()
                    //MiEdit()
                    MiEditAvanzado()
                    //MiEditOutLined()
                }
            }
        }
    }
}

@Composable
fun MiText(){
    Column (modifier = Modifier.fillMaxSize()) {
        Text(text = "Ejemplo de texts")
        Text(text = "Ejemplo de texts", color = Color.Cyan)
        Text(text = "Ejemplo de texts", color = Color.Green, fontWeight = FontWeight.Bold)
        Text(text = "Ejemplo de texts", color = Color.Blue, style = TextStyle(fontFamily = FontFamily.SansSerif, fontSize = 20.sp), fontStyle = FontStyle.Italic)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiEditOutLined(){
    //Como las editText cambian de valor tienen asociado un estado por lo que:
    var miEditText by remember { mutableStateOf("") }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center ) {//Si no lo ponemos en un layout ocupa todo.
        OutlinedTextField(
            value = miEditText,
            onValueChange = {   miEditText = it },
            label = {Text("Introduce un nombre")},
            modifier = Modifier.padding(24.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Green,
                unfocusedBorderColor = Color.Blue
            )
        )
    }
}

/**
 * También hemos configurado un ImeAction.Done y KeyboardActions para cerrar el teclado cuando se toca "Done" en el teclado virtual.
 */
@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun MiEditAvanzado(){
    //Como las editText cambian de valor tienen asociado un estado por lo que:
    var miEditText by remember { mutableStateOf("") }

//    val focusManager = LocalFocusManager.current
//    val keyboardController = LocalSoftwareKeyboardController.current
//    val focusRequester = remember { FocusRequester() }

    TextField(
            value = miEditText,
            onValueChange = {   miEditText = it.uppercase()  },
            label = {Text("Introduce un nombre")},
//            keyboardOptions = KeyboardOptions.Default.copy(
//                imeAction = ImeAction.Done
//            ),
//            keyboardActions = KeyboardActions(
//                onDone = {
//                    focusManager.clearFocus()
//                    keyboardController?.hide()
//                }
//            )
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiEdit(){
    //Como las editText cambian de valor tienen asociado un estado por lo que:
    var miEditText by remember { mutableStateOf("") }
    TextField(value = miEditText, onValueChange = {
        //El valor de it será el que hayamos escrito en la editText.
        miEditText = it
    })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TextsTheme {

    }
}