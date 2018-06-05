package com.amanarora.restauranttipcalculator.viewmodel

import com.amanarora.restauranttipcalculator.model.RestaurantCalculator
import com.amanarora.restauranttipcalculator.model.TipCalculation
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CalculatorViewModelTest {

    lateinit var calculatorViewModel : CalculatorViewModel

    @Mock
    lateinit var mockCalculator : RestaurantCalculator

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        calculatorViewModel = CalculatorViewModel(mockCalculator)
    }

    @Test
    fun calculateTestTip() {
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = "15"

        val stub = TipCalculation(checkAmount = 10.00, tipAmount = 1.5, grandTotal = 11.5)
        `when`(mockCalculator.calculateTip(10.00,15)).thenReturn(stub)

        calculatorViewModel.calculateTip()

        assertEquals(stub, calculatorViewModel.tipCalculation)
       /* assertEquals(10.00, calculatorViewModel.tipCalculation.checkAmount)
        assertEquals(15, calculatorViewModel.tipCalculation.tipPct)
        assertEquals(11.50, calculatorViewModel.tipCalculation.grandTotal)*/
    }

    @Test
    fun testCalculateTipBadTipPercent() {
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = ""

        calculatorViewModel.calculateTip()

        verify(mockCalculator, never()).calculateTip(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyInt())
    }

    @Test
    fun testCalculateTipBadCheckAmount() {
        calculatorViewModel.inputCheckAmount = ""
        calculatorViewModel.inputTipPercentage = "15"

        calculatorViewModel.calculateTip()

        verify(mockCalculator, never()).calculateTip(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyInt())
    }




}