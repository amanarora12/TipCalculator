package com.amanarora.restauranttipcalculator.model

import android.arch.lifecycle.LiveData
import java.math.RoundingMode

class RestaurantCalculator(val repository: TipCalculationRepository = TipCalculationRepository()) {

    fun calculateTip(checkInput: Double, tipPct: Int) : TipCalculation {
        val tipAmount = (checkInput * (tipPct.toDouble()/100.0))
                .toBigDecimal()
                .setScale(2,RoundingMode.HALF_UP)
                .toDouble()
        val grandTotal = checkInput + tipAmount
        return TipCalculation(checkInput, tipPct, tipAmount, grandTotal)
    }

    fun saveTipCalculation(tc: TipCalculation) {
        repository.saveTipCalculation(tc)
    }

    fun loadTipCalculationByLocationName(locationName: String) : TipCalculation? {
        return repository.loadTipCalculationByName(locationName)
    }

    fun loadSavedTipCalculations() : LiveData<List<TipCalculation>> {
        return repository.loadSavedTipCalculations()
    }
}