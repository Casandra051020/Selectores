# Selectores

Proyecto Android desarrollado en **Kotlin** con **Jetpack Compose**, correspondiente a la práctica *"Componentes de selección"* de la materia **Desarrollo para Dispositivos Inteligentes**.

## Objetivo

Aplicar las propiedades de los componentes de selección u opción (`RadioButton`, `Chip`, `CheckBox` y `Switch`) para el desarrollo de aplicaciones móviles, implementando un convertidor de unidades de longitud (metros a pies, pulgadas y yardas).

## Descripción

La app cuenta con una barra de pestañas (`PrimaryTabRow`) con 4 secciones, cada una implementando el convertidor con un componente de selección distinto:

| Pestaña | Componente | Tipo de selección |
|---|---|---|
| RadioBtn | `RadioButton` | Única (elige una unidad) |
| FilterChips | `FilterChip` | Múltiple |
| Checkbox | `Checkbox` | Múltiple |
| Switch | `Switch` | Múltiple (activar/desactivar) |

En cada pantalla el usuario:
1. Ingresa una cantidad en metros.
2. Selecciona la(s) unidad(es) a convertir.
3. Presiona **Convertir** para calcular, **Mostrar** para ver el resultado, o **Limpiar** para reiniciar el formulario.

## Estructura del proyecto

```
com.example.selectores/
├── MainActivity.kt              # Punto de entrada, Scaffold con TabRow y navegación entre pantallas
├── Convertidor.kt                # Modelo de dominio: lógica de conversión de metros
├── RadioConverterScreen.kt       # Pantalla con RadioButton
├── ChipConverterScreen.kt        # Pantalla con FilterChip
├── CheckboxConverterScreen.kt    # Pantalla con Checkbox
├── SwitchConverterScreen.kt      # Pantalla con Switch
└── ui/theme/                     # Tema de Compose (colores, tipografía)
```

## Clase Convertidor

Encapsula los valores y el cálculo de conversión a partir de una cantidad en metros:

- `feet` — metros × 3.2808
- `inch` — metros × 39.3701
- `yard` — metros × 1.09361

Incluye métodos `calculateFeet()`, `calculateInch()`, `calculateYard()`, `clear()` y funciones para obtener los resultados formateados con dos decimales (`getFormattedFeet()`, `getFormattedInch()`, `getFormattedYard()`).

## Requisitos

- Android Studio (versión reciente)
- SDK mínimo: API 30 (Android 11.0)
- Kotlin + Jetpack Compose (Material 3)

## Cómo ejecutar

1. Clonar o abrir el proyecto en Android Studio.
2. Esperar a que Gradle sincronice las dependencias.
3. Ejecutar en un emulador o dispositivo físico (▶ Run).
4. Navegar entre las 4 pestañas para probar cada componente de selección.

## Recursos utilizados

- Iconos vectoriales (`res/drawable`) para los botones de Convertir, Limpiar y Mostrar, generados con **Vector Asset > Clip Art**.

## Autora

Casandra — Universidad Tecnológica de Jalisco (UTJ)
Materia: Desarrollo para Dispositivos Inteligentes
