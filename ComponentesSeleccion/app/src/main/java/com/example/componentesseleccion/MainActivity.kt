package com.example.componentesseleccion

import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.componentesseleccion.ui.theme.ComponentesSeleccionTheme
import kotlinx.coroutines.selects.select

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
                    var context = LocalContext.current
//                    Column {
//                        MiSwitch()
//                        IconSwitchPreview()
//                    }
//                    MiCheckBox()
//                    Column() {
//                        MiCheckBoxTexto("Opción 1")
//                        MiCheckBoxTexto("Opción 2")
//                        MiCheckBoxTexto("Opción 3")
//                        MiCheckBoxTexto("Opción 4")
//                    }

                    //-------------------------------------------------------
                    //Para un checkBox.
//                    var estado by remember { mutableStateOf(false) }
//                    var checkInfo = CheckInfo(
//                        title="Opción 1",
//                        selected = estado,
//                        onCheckedChange = {estado = it}
//                    )
//                    Column {
//                        MiCheckBoxObjeto(checkInfo)
//                        Button(onClick = {
//                            if (checkInfo.selected) {
//                                Toast.makeText(context, "Seleccionado", Toast.LENGTH_SHORT).show()
//                            }
//                            else {
//                                Toast.makeText(context, "No seleccionado", Toast.LENGTH_SHORT).show()
//                            }
//                        }) {
//                            Text(text = "Comprobar")
//                        }
//                    }

                    //-------------------------------------------------------
//                    Un conjunto de CheckBox.
//                    Column {
//                        var alTitulosCheckBox =
//                            generarOpciones(titulos = listOf("Opciones 1", "Opción 2", "Ejemplo 3"))
//                        alTitulosCheckBox.forEach {
//                            MiCheckBoxObjeto(info = it)
//                        }
//                        var seleccionados = ArrayList<String>()
//                        Button(onClick = {
//                            seleccionados.clear()
//                            alTitulosCheckBox.forEach {
//                                if (it.selected) {
//                                    seleccionados.add(it.title)
//                                }
//                            }
//                            Toast.makeText(context, seleccionados.toString(), Toast.LENGTH_SHORT)
//                                .show()
//                        }) {
//                            Text(text = "Comprobar")
//                        }
//                    }


                    //----------------- RadioButtons -----
                    //MiRadioButton()
                    MiRadiButtonsList()
                }
            }
        }
    }


    @Composable
    fun MiRadiButtonsList(){
        var selected by remember {
            mutableStateOf("Op1")
        }
        Column(Modifier.fillMaxWidth()) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                RadioButton(selected = selected == "Op1", onClick = { selected = "Op1" })
                Text(text = "Op1")
            }
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                RadioButton(selected = selected == "Op2", onClick = {  selected = "Op2" })
                Text(text = "Op2")
            }
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                RadioButton(selected = selected == "Op3", onClick = { selected = "Op3" })
                Text(text = "Op3")
            }
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                RadioButton(selected = selected == "Op4", onClick = { selected = "Op4" })
                Text(text = "Op4")
            }
        }
    }

    @Composable
    fun MiRadioButton(){
        Row(Modifier.fillMaxWidth()){
            RadioButton(selected = false, onClick = {  }, colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Yellow,
                disabledSelectedColor = Color.Gray
            ))
            Text(text = "Ejemplo RB")
        }
    }

    @Composable
    fun generarOpciones(titulos: List<String>): ArrayList<CheckInfo> {
        var AlInfo = ArrayList<CheckInfo>()
        titulos.forEach {
            var estado by remember { mutableStateOf(false) }
            var estadoEnable by remember { mutableStateOf(true) }
            var chInfo = CheckInfo(
                title = it,
                selected = estado,
                enable = estadoEnable,
                onCheckedChange = { estado = it }
            )
            AlInfo.add(chInfo)
        }
        return AlInfo
    }

    @Composable
    fun MiSwitch() {
        var estado by remember { mutableStateOf(true) }
        Switch(
            checked = estado,
            onCheckedChange = {
                Log.e("Fernando", "Switch pulsado: $it")
                //estado = !estado
                estado = it
                              },
            enabled = true,
//            colors = SwitchDefaults.colors(
//                checkedThumbColor = Color.Green,
//                checkedTrackColor = Color.Red,
//                uncheckedThumbColor = Color.Magenta,
//                uncheckedTrackColor = Color.Yellow
//            )
        )
    }

    @Composable
    fun MiCheckBox() {
        var estado by remember { mutableStateOf(true) }
        var estadoEnable by remember { mutableStateOf(true) }
        Checkbox(
            checked = estado,
            onCheckedChange = { estado = !estado },
            enabled = estadoEnable,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Green,
                uncheckedColor = Color.Red,
                checkmarkColor = Color.Magenta
            )
        )
    }

    @Composable
    fun MiCheckBoxObjeto(info: CheckInfo) {
        Row() {
            Checkbox(
                checked = info.selected,
                enabled = info.enable,
                onCheckedChange = { info.onCheckedChange(!info.selected) })
            Text(info.title, modifier = Modifier.padding(vertical = 12.dp))
        }
    }

    @Composable
    fun MiCheckBoxTexto(texto: String) {
        var estado by remember { mutableStateOf(true) }
        var estadoEnable by remember { mutableStateOf(true) }
        Row() {
            Checkbox(
                checked = estado,
                enabled = estadoEnable,
                onCheckedChange = { estado = !estado })
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
}