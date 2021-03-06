package com.amanarora.restauranttipcalculator.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.util.Log
import com.amanarora.restauranttipcalculator.R
import com.amanarora.restauranttipcalculator.model.RestaurantCalculator
import com.amanarora.restauranttipcalculator.model.TipCalculation

class CalculatorViewModel @JvmOverloads constructor(
        app: Application, val calculator : RestaurantCalculator = RestaurantCalculator()) : ObservableViewModel(app) {

    private var lastTipCalculated = TipCalculation()

    var inputCheckAmount = ""
    var inputTipPercentage = ""

    val outputCheckAmount get() = getApplication<Application>().getString(R.string.dollar_amount,lastTipCalculated.checkAmount)
    val outputTipAmount get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.tipAmount)
    val outputTotalAmount get() = getApplication<Application >().getString(R.string.dollar_amount, lastTipCalculated.grandTotal)
    val locationName get() = lastTipCalculated.locationName

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc: TipCalculation) {
        lastTipCalculated = tc
        notifyChange()
    }

    fun calculateTip() {
        Log.d(TAG, "Method Invoked")
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()
        if (checkAmount != null && tipPct != null) {
            Log.d(TAG, "Check Amount: $checkAmount and Tip Percent: $tipPct")
            updateOutputs(calculator.calculateTip(checkAmount, tipPct))
        }

    }

    fun saveCurrentTip(name: String) {
        Log.d(TAG, "saving location $name")
        val tipToSave = lastTipCalculated.copy(locationName = name)
        calculator.saveTipCalculation(tipToSave)
        updateOutputs(tipToSave)
    }

    fun loadSavedTipCalculationSummaries(): LiveData<List<TipCalculationSummaryItem>> {
        return Transformations.map(calculator.loadSavedTipCalculations(),{ tipCalculationObjects: List<TipCalculation> ->
            tipCalculationObjects.map {
                TipCalculationSummaryItem(it.locationName,
                        getApplication<Application>().getString(R.string.dollar_amount,it.grandTotal))
            }
        })
    }

    fun loadTipCalculation(name: String) {
        val tc = calculator.loadTipCalculationByLocationName(name)
        if (tc != null) {
            inputCheckAmount = tc.checkAmount.toString()
            inputTipPercentage = tc.tipPct.toString()
            updateOutputs(tc)
            notifyChange()
        }
    }
}

private const val TAG = "CalculatorViewModel"