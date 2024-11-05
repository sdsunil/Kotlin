package com.example.demo.controller

import com.example.demo.models.Bank
import com.example.demo.services.BankService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/banks")
class BankController(val service: BankService) {

    @GetMapping
    fun getAllBanks(): List<Bank> {
        return service.getAllBanks()
    }

    @GetMapping("/{id}")
    fun getBankById(@PathVariable id: String): ResponseEntity<Bank> {
        val bank = service.getBank(id)
        return if (bank != null) {
            ResponseEntity.ok(bank)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/add")
    fun addBank(@RequestBody bank: Bank): ResponseEntity<List<Bank>> {
        val updatedBanks = service.addBank(bank)
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedBanks)
    }
}
