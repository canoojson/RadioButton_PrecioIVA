package com.example.precioiva

import android.os.Bundle
import androidx.compose.material3.RadioButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.precioiva.ui.theme.PrecioIvaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrecioIvaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PrecioMasIva(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

fun calcularPrecioIva(precio: Double, IVA: Double): Double{
    var res = precio + precio * IVA/100

    return res
}

@Composable
fun PrecioMasIva(modifier: Modifier = Modifier){

    val radioOptions = listOf(21,10,3)

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    var input by remember { mutableStateOf("") }

    val precio = input.toDoubleOrNull() ?: 0.0

    val IVA = selectedOption.toDouble()

    val precioIVA = calcularPrecioIva(precio, IVA)



    Column(
        modifier = Modifier.fillMaxSize().padding(25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       TextField(
           value = input,
           onValueChange = {input = it},
           label = { Text(text = "Precio del producto")},
           singleLine = true,
           keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
       )
        radioOptions.forEach{ text ->
            Row(
                Modifier.fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = {onOptionSelected(text) }
                )
                Text(
                    text = text.toString() + "%",
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
        Text(
            text = "Precio con IVA: " + precioIVA + "€"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PrecioIvaTheme {
        PrecioMasIva()
    }
}