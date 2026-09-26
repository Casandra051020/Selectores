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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class ConversionType {
    FEET, INCH, YARD
}

@Composable
fun RadioConverterScreen(convertidor: Convertidor) {
    val context = LocalContext.current

    // Estados observados por Jetpack Compose
    var inputMetros by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf(ConversionType.FEET) }
    var resultText by remember { mutableStateOf("Cantidad de metros convertida a:\n") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Título
        Text(
            text = "Convertidor con RadioButton",
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

        // Grupo de RadioButtons
        Text(
            text = "Seleccione unidad a calcular:",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.Start)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ConversionType.entries.forEach { option ->
                val label = when (option) {
                    ConversionType.FEET -> "Pies"
                    ConversionType.INCH -> "Pulgadas"
                    ConversionType.YARD -> "Yardas"
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .selectable(
                            selected = (selectedOption == option),
                            onClick = { selectedOption = option },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 4.dp)
                ) {
                    RadioButton(
                        selected = (selectedOption == option),
                        onClick = { selectedOption = option },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFFFF9800)
                        )
                    )
                    Text(text = label, modifier = Modifier.padding(start = 2.dp))
                }//Row
            }//ConversionType
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
                    when (selectedOption) {
                        ConversionType.FEET -> convertidor.calculateFeet()
                        ConversionType.INCH -> convertidor.calculateInch()
                        ConversionType.YARD -> convertidor.calculateYard()
                    }
                    Toast.makeText(context, "Metros convertidos.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Ingresa cantidad en metros.", Toast.LENGTH_SHORT).show()
                }
            }) {
                Icon(painter = painterResource(id = R.drawable.icon_convertir),
                    contentDescription = "Convertir",
                    tint = colorScheme.primary)
            }//IconButton

            // Limpiar
            IconButton(onClick = {
                inputMetros = ""
                convertidor.clear()
                resultText = "Cantidad de metros convertida a:\n"
            }) {
                Icon(painter = painterResource(id = R.drawable.icon_limpiar),
                    contentDescription = "Limpiar",
                    tint = colorScheme.primary)
            }//IconButton

            // Mostrar
            IconButton(onClick = {
                resultText = "Cantidad de metros convertida a:\n" +
                        "Pies: ${convertidor.getFormattedFeet()}\n" +
                        "Pulgadas: ${convertidor.getFormattedInch()}\n" +
                        "Yardas: ${convertidor.getFormattedYard()}\n"
            }) {
                Icon(painter = painterResource(id = R.drawable.icon_mostrar),
                    contentDescription = "Mostrar",
                    tint = colorScheme.secondary)
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
}//RadioConverterScreen