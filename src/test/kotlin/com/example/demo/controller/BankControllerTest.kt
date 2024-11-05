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
import org.springframework.http.ResponseEntity
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
        // Arrange
        val bank1 = Bank("1", "Bank A")
        val bank2 = Bank("2", "Bank B")
        val banks = listOf(bank1, bank2)

        `when`(bankService.getAllBanks()).thenReturn(banks)

        // Act
        val result = bankController.getAllBanks()

        // Assert
        assertEquals(2, result.size)
        assertEquals("Bank A", result[0].name)
        verify(bankService, times(1)).getAllBanks()
    }

    @Test
    fun `test getBankById returns bank when found`() {
        // Arrange
        val bankId = "1"
        val bank = Bank(bankId, "Bank A")
        `when`(bankService.getBank(bankId)).thenReturn(bank)

        // Act
        val response = bankController.getBankById(bankId)

        // Assert
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(bank, response.body)
        verify(bankService, times(1)).getBank(bankId)
    }

    @Test
    fun `test getBankById returns NOT_FOUND when bank not found`() {
        // Arrange
        val bankId = "999"
        `when`(bankService.getBank(bankId)).thenReturn(null)

        // Act
        val response = bankController.getBankById(bankId)

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        verify(bankService, times(1)).getBank(bankId)
    }

    @Test
    fun `test addBank returns CREATED status and updated list`() {
        // Arrange
        val bank = Bank("1", "Bank A")
        val updatedBanks = listOf(bank)
        `when`(bankService.addBank(bank)).thenReturn(updatedBanks)

        // Act
        val response = bankController.addBank(bank)

        // Assert
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(1, response.body?.size)
        assertEquals("Bank A", response.body?.get(0)?.name)
        verify(bankService, times(1)).addBank(bank)
    }
}
