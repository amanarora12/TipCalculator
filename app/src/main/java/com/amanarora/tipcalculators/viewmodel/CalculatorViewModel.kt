package com.amanarora.tipcalculators.viewmodel

import com.amanarora.tipcalculators.model.RestaurantCalculator
import com.amanarora.tipcalculators.model.TipCalculation

class CalculatorViewModel(val calculator : RestaurantCalculator = RestaurantCalculator()) {

    var inputCheckAmount = ""

    var inputTipPercentage = ""

    var tipCalculation = TipCalculation()

    fun calculateTip() {
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()
        if (checkAmount != null && tipPct != null)
            tipCalculation = calculator.calculateTip(checkAmount, tipPct)
    }
}