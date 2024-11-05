package com.example.demo.aop
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component
import java.util.*

@Aspect
@Component
class BankAspect {

    // first * for methode return second * for methode name and (..) for methode parameters
    @Before("execution(* com.example.demo.controller.BankController.*(..))")
    fun beforeAdvice(joinPoint: JoinPoint) {
        println("Request to " + joinPoint.signature + " started at " + Date())
    }
}
