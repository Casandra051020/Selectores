package com.example.selectores

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Pantalla que utiliza CheckBox para seleccionar una o varias conversiones,
// siguiendo el mismo patrón que RadioConverterScreen y ChipConverterScreen.
@Composable
fun CheckboxConverterScreen(convertidor: Convertidor) {
    val context = LocalContext.current

    // Estado del formulario
    var inputMetros by remember { mutableStateOf("") }

    // Estados independientes para cada CheckBox (selección múltiple)
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
        // Título
        Text(
            text = "Convertidor con CheckBox",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Caja de Texto para Metros
        OutlinedTextField(
            value = inputMetros,
            onValueChange = { inputMetros = it },
            label = { Text("Metros") },
            placeholder = { Text("Ingrese cantidad en metros") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Seleccione una o más conversiones:",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.Start)
        )

        // Fila de CheckBoxes
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // CheckBox Pies
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = feetChecked,
                    onCheckedChange = { feetChecked = it },
                    colors = CheckboxDefaults.colors(checkedColor = colorScheme.primary)
                )
                Text(text = "Pies")
            }//Row

            // CheckBox Pulgadas
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = inchChecked,
                    onCheckedChange = { inchChecked = it },
                    colors = CheckboxDefaults.colors(checkedColor = colorScheme.primary)
                )
                Text(text = "Pulgadas")
            }//Row

            // CheckBox Yardas
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = yardChecked,
                    onCheckedChange = { yardChecked = it },
                    colors = CheckboxDefaults.colors(checkedColor = colorScheme.primary)
                )
                Text(text = "Yardas")
            }//Row
        }//Row

        Spacer(modifier = Modifier.height(24.dp))

        // Botones de Acción (Convertir, Limpiar, Mostrar)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Convertir
            IconButton(onClick = {
                if (inputMetros.isNotBlank() && inputMetros.isNotEmpty()) {
                    convertidor.meter = inputMetros.toIntOrNull() ?: 0
                    if (feetChecked) convertidor.calculateFeet()
                    if (inchChecked) convertidor.calculateInch()
                    if (yardChecked) convertidor.calculateYard()
                    Toast.makeText(context, "Metros convertidos.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Ingresa cantidad en metros.", Toast.LENGTH_SHORT).show()
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_convertir),
                    contentDescription = "Convertir",
                    tint = colorScheme.primary
                )
            }//IconButton

            // Limpiar
            IconButton(onClick = {
                inputMetros = ""
                feetChecked = false
                inchChecked = false
                yardChecked = false
                convertidor.clear()
                resultText = "Cantidad de metros convertidos a:\n"
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_limpiar),
                    contentDescription = "Limpiar",
                    tint = colorScheme.error
                )
            }//IconButton

            // Mostrar
            IconButton(onClick = {
                resultText = "Cantidad de metros convertidos a:\n" +
                        "Pies: ${convertidor.getFormattedFeet()}\n" +
                        "Pulgadas: ${convertidor.getFormattedInch()}\n" +
                        "Yardas: ${convertidor.getFormattedYard()}\n"
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_mostrar),
                    contentDescription = "Mostrar",
                    tint = colorScheme.secondary
                )
            }//IconButton
        }//Row

        Spacer(modifier = Modifier.height(24.dp))

        // Área de Resultado
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            color = colorScheme.primaryContainer,
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = resultText,
                modifier = Modifier.padding(16.dp),
                color = colorScheme.onPrimaryContainer,
                fontSize = 16.sp
            )
        }//Surface
    }//Column
}//CheckboxConverterScreen