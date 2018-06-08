package com.amanarora.restauranttipcalculator.model

import android.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TipCalculationRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule( )

    lateinit var repository : TipCalculationRepository

    @Before
    fun setup() {
        repository = TipCalculationRepository()
    }

    @Test
    fun testSaveTip() {
        val tip = TipCalculation(100.0, 25, 25.0,
                125.0,"Chocolate Factory")
        repository.saveTipCalculation(tip)

        assertEquals(tip, repository.loadTipCalculationByName(tip.locationName))
    }

    @Test
    fun testLoadSavedTipCalculations() {
        val tip1 = TipCalculation(100.0, 25, 25.0,
                125.0,"Chocolate Factory")
        val tip2  = TipCalculation(100.0, 25, 25.0,
                125.0,"Dunkin Donuts")
        repository.saveTipCalculation(tip1)
        repository.saveTipCalculation(tip2)

        repository.loadSavedTipCalculations().observeForever { tipCalculations ->
            assertEquals(2, tipCalculations?.size)
        }
    }
}