package com.example.layouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.layouts.ui.theme.LayoutsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //myBox("DAM2")
                    //myColumn()
                    //myRow()
                    //RowColBox()
                    //MisConstraintsLayouts()
                    //ConstraintExampleGuide()
                    //ConstraintExampleBarrier()
                    ConstraintExampleChains()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text("Hola a todos")
}

/**
 * Son equivalentes a LinearLayout.
 */
@Composable
fun myColumn(){
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.SpaceBetween) {
        Text(text="Otra cosa", modifier = Modifier
            .background(Color.Red)
            .fillMaxWidth()
            .height(100.dp))
        Text(text="Hola", modifier = Modifier
            .background(Color.Green)
            .fillMaxWidth()
            .height(100.dp))
        Text(text="Ejemplo", modifier = Modifier
            .background(Color.Cyan)
            .fillMaxWidth()
            .height(100.dp))
        Text(text="Otro", modifier = Modifier
            .background(Color.Magenta)
            .fillMaxWidth()
            .height(100.dp))
        Text(text="Ejemplo", modifier = Modifier
            .background(Color.Cyan)
            .fillMaxWidth()
            .height(100.dp))
        Text(text="Hola", modifier = Modifier
            .background(Color.Green)
            .fillMaxWidth()
            .height(100.dp))
        Text(text="Hola", modifier = Modifier
            .background(Color.Green)
            .fillMaxWidth()
            .height(100.dp))
    }
}

/**
 * Igual que con column.
 */
@Composable
fun myRow(){
    Row(modifier = Modifier
        .fillMaxSize()
        .horizontalScroll(rememberScrollState())) {
        Text(text="Otra cosa", modifier = Modifier
            .background(Color.Red)
            .width(100.dp))
        Text(text="Hola", modifier = Modifier
            .background(Color.Green)
            .width(100.dp))
        Text(text="Ejemplo", modifier = Modifier
            .background(Color.Cyan)
            .width(100.dp))
        Text(text="Hola", modifier = Modifier
            .background(Color.Green)
            .width(100.dp))
    }
}

/**
 * Los Box son los equivalentes a los FrameLayouts. Su colocación es en las posiciones estándar: arriba izquierda, centro arriba, arriba derecha, centro derecha, centro, centro izquierda, abajo derecha, abajo centro y abajo izquierda.
 */
@Composable
fun myBox(name:String){

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
//      Box(modifier = Modifier.width(50.dp).height(50.dp).background(Color.Cyan))//Si ponemos tamaño se ajusta a ese tamaño
        //Si no ponemos tamaño es el equivalente al wrap_content.
        Box(modifier = Modifier
            .background(Color.Cyan)
            //.height(20.dp)
            //.width(50.dp)
            .verticalScroll( //ESto activa automáticamente el scroll caso de que el tamaño del box no deje ver el contenido.
                rememberScrollState()
            ), contentAlignment = Alignment.Center
        )
        {
            Text("Texto dentro del BOX $name")
        }
    }
}

/**
 * Combinando Row, Column y BOx.
 */
@Composable
fun RowColBox(){
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Green)) {

        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .background(Color.Blue))
        Row (
            Modifier
                .fillMaxWidth()
                .weight(1f)) {
            Box (
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(Color.Red), contentAlignment = Alignment.BottomCenter){
                Text("Esto va en este box")
            }
            Box (
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(Color.Cyan), contentAlignment = Alignment.Center){
                Text("Esto va en esta celda")
            }
        }
        Box (
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Blue)) {

        }
    }
}

/**
 * La misma filosofía que en XML.
 * Se debe implementar la dependencia: implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
 */
@Composable
fun MisConstraintsLayouts(){
    ConstraintLayout(modifier = Modifier.fillMaxSize()){
        val (boxRed, boxBlue, boxGreen, boxYellow) = createRefs()

        Box(modifier = Modifier.size(125.dp).background(Color.Red).constrainAs(boxRed){
            top.linkTo(boxYellow.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        Box(modifier = Modifier.size(125.dp).background(Color.Blue).constrainAs(boxBlue){
            top.linkTo(boxGreen.bottom)
            end.linkTo(boxYellow.start)
        })
        Box(modifier = Modifier.size(125.dp).background(Color.Green).constrainAs(boxGreen){
            bottom.linkTo(boxYellow.top)
            start.linkTo(boxYellow.end)
        })
        Box(modifier = Modifier.size(125.dp).background(Color.Yellow).constrainAs(boxYellow){
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}


@Composable
fun ConstraintExampleGuide(){
    ConstraintLayout(modifier = Modifier.fillMaxSize()){
        var boxBlue = createRef()
//        val startGuide = createGuidelineFromTop(16.dp) //Pero mejor usar porcentajes.
        val topGuide = createGuidelineFromTop(0.1f) //10%
        val startGuide = createGuidelineFromStart(0.25f)

        Box(modifier = Modifier.size(125.dp).background(Color.Red).constrainAs(boxBlue){
            top.linkTo(topGuide)
            start.linkTo(startGuide)
        })
    }
}

@Composable
fun ConstraintExampleBarrier(){
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (boxBlue, boxGreen, boxMag) = createRefs()
        val miBarrier = createEndBarrier(boxBlue, boxGreen) //Se crea una "jaula" para los elementos incluidos.

        Box(modifier = Modifier.size(125.dp).background(Color.Blue).constrainAs(boxBlue){
            start.linkTo(parent.start, margin = 10.dp)
        })
        Box(modifier = Modifier.size(250.dp).background(Color.Green).constrainAs(boxGreen){
            top.linkTo(boxBlue.bottom)
            start.linkTo(parent.start, margin = 50.dp)

        })
        Box(modifier = Modifier.size(125.dp).background(Color.Magenta).constrainAs(boxMag){
            start.linkTo(miBarrier)
        })
    }
}

@Preview
@Composable
fun ConstraintExampleChains(){
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (boxBlue, boxGreen, boxMag) = createRefs()

        Box(modifier = Modifier.size(90.dp).background(Color.Blue).constrainAs(boxBlue){
            start.linkTo(parent.start)
            end.linkTo(boxGreen.start)
        })
        Box(modifier = Modifier.size(90.dp).background(Color.Green).constrainAs(boxGreen){
            start.linkTo(boxBlue.end)
            end.linkTo(boxMag.start)
        })
        Box(modifier = Modifier.size(90.dp).background(Color.Magenta).constrainAs(boxMag){
            start.linkTo(boxGreen.end)
            end.linkTo(parent.end)
        })

        //Las cadenas es una forma de agrupar controles.
        //createHorizontalChain(boxBlue, boxGreen,boxMag, chainStyle = ChainStyle.Packed)
        //createHorizontalChain(boxBlue, boxGreen,boxMag, chainStyle = ChainStyle.Spread)
        createHorizontalChain(boxBlue, boxGreen,boxMag, chainStyle = ChainStyle.SpreadInside)
    }
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LayoutsTheme {
        //myColumn()
        //myRow()
        //RowColBox()
        //MisConstraintsLayouts()
    }
}