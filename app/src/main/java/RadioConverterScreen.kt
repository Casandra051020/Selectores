import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

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
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )
    }
}
