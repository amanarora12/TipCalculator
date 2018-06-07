package com.amanarora.restauranttipcalculator.viewmodel

import android.app.Application
import android.databinding.BaseObservable
import android.util.Log
import com.amanarora.restauranttipcalculator.R
import com.amanarora.restauranttipcalculator.model.RestaurantCalculator
import com.amanarora.restauranttipcalculator.model.TipCalculation

class CalculatorViewModel(val app: Application, val calculator : RestaurantCalculator = RestaurantCalculator()) : BaseObservable() {

    var inputCheckAmount = ""
    var inputTipPercentage = ""

    var outputCheckAmount = ""
    var outputTipAmount = ""
    var outputTotalAmount = ""

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc: TipCalculation) {
        outputCheckAmount = app.getString(R.string.dollar_amount,tc.checkAmount)
        outputTipAmount = app.getString(R.string.dollar_amount, tc.tipAmount)
        outputTotalAmount = app.getString(R.string.dollar_amount, tc.grandTotal)
    }

    fun calculateTip() {
        Log.d(TAG, "Method Invoked")
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()
        if (checkAmount != null && tipPct != null) {
            Log.d(TAG, "Check Amount: $checkAmount and Tip Percent: $tipPct")
            updateOutputs(calculator.calculateTip(checkAmount, tipPct))
            clearInputs()
        }

    }

    private fun clearInputs() {
        inputCheckAmount = "0.00"
        inputTipPercentage = "0"
        notifyChange()
    }
}

private const val TAG = "CalculatorViewModel"