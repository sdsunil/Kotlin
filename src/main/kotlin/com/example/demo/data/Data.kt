package com.example.demo.data

import com.example.demo.models.Bank
import org.springframework.stereotype.Component

@Component
class Data {
    val banks = mutableListOf(
        Bank("Amit", "123"),
        Bank("karan", "124"),
        Bank("ram", "125"),
    )

    fun allBanks(): List<Bank> {
        return banks
    }

    fun getBank(id: String): Bank? {
        return banks.firstOrNull { it -> it.acc == id }
    }

    fun addBank(bank: Bank): List<Bank>{
        banks.add(bank)
        return banks
    }
}
