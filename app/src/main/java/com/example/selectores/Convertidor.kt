package com.example.selectores

import java.text.DecimalFormat

class Convertidor  (var meter: Int =0){
//Atributos
    var feet :Double = 0.0
    private set
    var inch: Double = 0.0
        private set
    var yard: Double = 0.0
        private set

    //Formato estándar para presentar los resultados con dos decimales

    private val formatoDecimales = DecimalFormat("#.##")

    fun calculateFeet(){
        if (this.meter>0){
            this.feet=this.meter *3.2808
        } else{
            this.feet=0.0
        }
    }//CalculateFeet

    fun calculateInch(){
        if (this.meter>0){
            this.inch=this.meter *39.3701
        } else{
            this.inch=0.0
        }
    }//CalculateInch

    fun calculateYard(){
        if (this.meter>0){
            this.yard=this.meter *1.09361
        } else{
            this.yard=0.0
        }
    }//CalculateYard

    fun clear() {
        meter = 0
        feet = 0.0
        inch = 0.0
        yard= 0.0
    }

    fun getFormattedFeet(): String = formatoDecimales.format (feet)
    fun getFormattedInch(): String = formatoDecimales.format (inch)
    fun getFormattedYard(): String = formatoDecimales.format (yard)
} //Class