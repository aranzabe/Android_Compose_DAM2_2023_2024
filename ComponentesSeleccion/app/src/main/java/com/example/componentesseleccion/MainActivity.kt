package com.example.componentesseleccion

import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.componentesseleccion.ui.theme.ComponentesSeleccionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComponentesSeleccionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //MiSwitch()
                    //IconSwitchPreview()
                    //MiCheckBox()
                    Column() {
                        MiCheckBoxTexto("Opción 1")
                        MiCheckBoxTexto("Opción 2")
                        MiCheckBoxTexto("Opción 3")
                        MiCheckBoxTexto("Opción 4")
                    }
                }
            }
        }
    }
}

@Composable
fun MiSwitch(){
    var estado by remember {mutableStateOf(true)}
    Switch(
        checked = estado,
        onCheckedChange = {estado = !estado},
        enabled = true,
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.Green,
            checkedTrackColor = Color.Red,
            uncheckedThumbColor = Color.Magenta,
            uncheckedTrackColor = Color.Yellow
        )
    )
}

@Composable
fun MiCheckBox(){
    var estado by remember {mutableStateOf(true)}
    Checkbox(
        checked = estado,
        onCheckedChange = {estado = !estado},
        enabled = true,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Green,
            uncheckedColor = Color.Red,
            checkmarkColor = Color.Magenta
        )
    )
}

@Composable
fun MiCheckBoxTexto(texto:String){
    var estado by remember {mutableStateOf(true)}
    Row(){
        Checkbox(checked = estado, onCheckedChange = {estado = !estado})
//        Spacer(modifier = Modifier.width(8.dp).height(2.dp))
        Text(texto, modifier = Modifier.padding(vertical = 12.dp))
    }
}

//-----------------------------------------------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconSwitch(
    icon: ImageVector,
    text: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.clickable { onCheckedChange(!isChecked) },
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(24.dp))
        TextField(
            value = text,
            onValueChange = { /* Do nothing for now */ },
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            modifier = Modifier.padding(start = 8.dp)
        )
        Switch(checked = isChecked, onCheckedChange = onCheckedChange)
    }
}

@Preview
@Composable
fun IconSwitchPreview() {
    var isChecked by remember { mutableStateOf(true) }

    ComponentesSeleccionTheme {
        IconSwitch(
            icon = Icons.Default.Settings,
            text = "Settings",
            isChecked = isChecked,
            onCheckedChange = { isChecked = it }
        )
    }
}