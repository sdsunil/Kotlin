package com.example.demo.custome.exception


class EmptyInputException(
    message: String,
    val errorCode: Int = 400,
    val details: String? = null
) : RuntimeException(message) {

    override fun toString(): String {
        return "EmptyInputException(errorCode=$errorCode, message=$message, details=$details)"
    }

    companion object {
        fun emptyNameException() = EmptyInputException("Bank name cannot be empty", 400)
        fun emptyIdException() = EmptyInputException("Bank ID cannot be empty", 400)
    }
}
