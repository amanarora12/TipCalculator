package com.amanarora.tipcalculators.model

import java.math.RoundingMode

class RestaurantCalculator {

    fun calculateTip(checkInput: Double, tipPct: Int) : TipCalculation {
        val tipAmount = (checkInput * (tipPct.toDouble()/100.0))
                .toBigDecimal()
                .setScale(2,RoundingMode.HALF_UP)
                .toDouble()
        val grandTotal = checkInput + tipAmount
        return TipCalculation(checkInput, tipPct, tipAmount, grandTotal)
    }
}