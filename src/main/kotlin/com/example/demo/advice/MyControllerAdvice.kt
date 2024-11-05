package com.example.demo.advice

import com.example.demo.custome.exception.EmptyInputException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class MyControllerAdvice {

    @ExceptionHandler(EmptyInputException::class)
    fun handleEmptyInputException(ex: EmptyInputException): ResponseEntity<Map<String, Any?>> {
        val errorResponse = mapOf(
            "errorCode" to ex.errorCode,
            "message" to ex.message,
            "details" to ex.details,
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }
}
