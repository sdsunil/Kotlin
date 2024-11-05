package com.example.demo.services

import com.example.demo.custome.exception.EmptyInputException
import com.example.demo.data.Data
import com.example.demo.models.Bank
import org.springframework.stereotype.Service

@Service
class BankService(val data: Data) {

    fun getAllBanks(): List<Bank> {
        return data.allBanks()
    }

    fun getBank(id: String): Bank? {
        return data.getBank(id)
    }
    fun addBank(bank: Bank): List<Bank> {
        if (bank.name.isBlank()) {
            throw EmptyInputException.emptyNameException()
        }
        if (bank.acc.isBlank()) {
            throw EmptyInputException.emptyNameException()
        }
        return data.addBank(bank)
    }
}
