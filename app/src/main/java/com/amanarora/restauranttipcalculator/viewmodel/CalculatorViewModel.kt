package com.amanarora.restauranttipcalculator.viewmodel

import com.amanarora.restauranttipcalculator.model.RestaurantCalculator
import com.amanarora.restauranttipcalculator.model.TipCalculation

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