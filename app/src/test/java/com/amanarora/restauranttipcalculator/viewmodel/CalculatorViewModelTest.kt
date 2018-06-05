package com.amanarora.restauranttipcalculator.viewmodel

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatorViewModelTest {

    lateinit var calculatorViewModel : CalculatorViewModel

    @Before
    fun setup() {
        calculatorViewModel = CalculatorViewModel()
    }

    @Test
    fun calculateTestTip() {
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = "15"

        calculatorViewModel.calculateTip()

        assertEquals(10.00, calculatorViewModel.tipCalculation.checkAmount)
        assertEquals(15, calculatorViewModel.tipCalculation.tipPct)
        assertEquals(11.50, calculatorViewModel.tipCalculation.grandTotal)
    }


}