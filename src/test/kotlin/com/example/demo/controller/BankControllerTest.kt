package com.example.demo.controller

import com.example.demo.models.Bank
import com.example.demo.services.BankService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals

class BankControllerTest {

    @Mock
    private lateinit var bankService: BankService

    @InjectMocks
    private lateinit var bankController: BankController

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test getAllBanks returns list of banks`() {
        val bank1 = Bank("1", "Bank A")
        val bank2 = Bank("2", "Bank B")
        val banks = listOf(bank1, bank2)
        `when`(bankService.getAllBanks()).thenReturn(banks)
        val result = bankController.getAllBanks()
        assertEquals(2, result.size)
        assertEquals("1", result[0].name)
        verify(bankService, times(1)).getAllBanks()
    }

    @Test
    fun `test getBankById returns bank when found`() {
        val bankId = "1"
        val bank = Bank(bankId, "Bank A")
        `when`(bankService.getBank(bankId)).thenReturn(bank)
        val response = bankController.getBankById(bankId)
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(bank, response.body)
        verify(bankService, times(1)).getBank(bankId)
    }

    @Test
    fun `test getBankById returns NOT_FOUND when bank not found`() {
        val bankId = "999"
        `when`(bankService.getBank(bankId)).thenReturn(null)
        val response = bankController.getBankById(bankId)
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        verify(bankService, times(1)).getBank(bankId)
    }

    @Test
    fun `test addBank returns CREATED status and updated list`() {
        val bank = Bank("1", "Bank A")
        val updatedBanks = listOf(bank)
        `when`(bankService.addBank(bank)).thenReturn(updatedBanks)
        val response = bankController.addBank(bank)
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(1, response.body?.size)
        assertEquals("1", response.body?.get(0)?.name)
        verify(bankService, times(1)).addBank(bank)
    }
}
