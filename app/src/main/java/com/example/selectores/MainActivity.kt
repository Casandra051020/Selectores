package com.example.selectores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.selectores.ui.theme.SelectoresTheme

/* Práctica para trabajar los componentes de tipo opción:
* -RadioButton
* -CheckBox
* -FilterChip
* -Switch
* */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Instancia compartida del modelo de dominio
        val convertidor = Convertidor()

        setContent {
            SelectoresTheme {
                MainAppScreen(convertidor = convertidor)
            }
        }
    }
}

@Composable
fun MainAppScreen(convertidor: Convertidor) {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("RadioBtn", "FilterChips", "Checkbox", "Switch")

    Scaffold(
        topBar = {
            PrimaryTabRow(
                selectedTabIndex = tabIndex,
                modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }//PrimaryTabRow
        }//topBar
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (tabIndex) {
                0 -> RadioConverterScreen(convertidor = convertidor)
                1 -> ChipConverterScreen(convertidor = convertidor)
                2 -> CheckboxConverterScreen(convertidor = convertidor)
                3 -> SwitchConverterScreen(convertidor = convertidor)
            }
        }
    }
}