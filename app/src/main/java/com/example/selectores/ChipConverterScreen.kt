package com.example.selectores

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipConverterScreen(convertidor: Convertidor) {
    val context = LocalContext.current

    // Estado del formulario
    var inputMetros by remember { mutableStateOf("") }

    // Estados independientes para cada Chip (Selección Múltiple)
    var feetChecked by remember { mutableStateOf(false) }
    var inchChecked by remember { mutableStateOf(false) }
    var yardChecked by remember { mutableStateOf(false) }

    var resultText by remember { mutableStateOf("Cantidad de metros convertidos a:\n") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Convertidor con FilterChips",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = inputMetros,
            onValueChange = { inputMetros = it },
            label = { Text("Metros") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Seleccione una o más conversiones:",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Fila / Contenedor de Chips
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Chip Pies
            FilterChip(
                selected = feetChecked,
                onClick = { feetChecked = !feetChecked },
                label = { Text("Pies") },
                leadingIcon = if (feetChecked) {
                    { Icon(Icons.Default.Check, contentDescription = null) }
                } else null
            )

            // Chip Pulgadas
            FilterChip(
                selected = inchChecked,
                onClick = { inchChecked = !inchChecked },
                label = { Text("Pulgadas") },
                leadingIcon = if (inchChecked) {
                    { Icon(Icons.Default.Check, contentDescription = null) }
                } else null
            )

            // Chip Yardas
            FilterChip(
                selected = yardChecked,
                onClick = { yardChecked = !yardChecked },
                label = { Text("Yardas") },
                leadingIcon = if (yardChecked) {
                    { Icon(Icons.Default.Check, contentDescription = null) }
                } else null
            )
        }//Row

        Spacer(modifier = Modifier.height(24.dp))

        // Botones de Operación
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Botón Convertir
            IconButton(onClick = {
                if (inputMetros.isNotBlank() && inputMetros.isNotEmpty()) {
                    convertidor.meter = inputMetros.toIntOrNull() ?: 0
                    // Evaluar cuáles chips fueron seleccionados
                    if (feetChecked) convertidor.calculateFeet()
                    if (inchChecked) convertidor.calculateInch()
                    if (yardChecked) convertidor.calculateYard()
                    Toast.makeText(context, "Metros convertidos.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Ingresa cantidad en metros.", Toast.LENGTH_SHORT).show()
                }
            }) {
                Icon(painterResource(id = R.drawable.icon_convertir),
                    contentDescription = "Convertir",
                    tint = MaterialTheme.colorScheme.primary)
            }//IconButton

            // Botón Limpiar
            IconButton(onClick = {
                inputMetros = ""
                feetChecked = false
                inchChecked = false
                yardChecked = false
                convertidor.clear()
                resultText = "Cantidad de metros convertidos a:\n"
            }) {
                Icon(painterResource(id = R.drawable.icon_limpiar),
                    contentDescription = "Limpiar",
                    tint = MaterialTheme.colorScheme.error)
            }//IconButton

            // Botón Mostrar
            IconButton(onClick = {
                resultText = "Cantidad de metros convertidos a:\n" +
                        "Pies: ${convertidor.getFormattedFeet()}\n" +
                        "Pulgadas: ${convertidor.getFormattedInch()}\n" +
                        "Yardas: ${convertidor.getFormattedYard()}\n"
            }) {
                Icon(painterResource(id = R.drawable.icon_mostrar),
                    contentDescription = "Mostrar",
                    tint = MaterialTheme.colorScheme.secondary)
            }//IconButton
        }//Row

        Spacer(modifier = Modifier.height(24.dp))

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),
            color = MaterialTheme.colorScheme.secondaryContainer,
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = resultText,
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = 16.sp
            )
        }//Surface
    }//Column
}//ChipConverterScreen