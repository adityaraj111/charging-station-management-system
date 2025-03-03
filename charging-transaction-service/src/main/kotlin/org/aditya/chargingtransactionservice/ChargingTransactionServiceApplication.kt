package org.aditya.chargingtransactionservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChargingTransactionServiceApplication

fun main(args: Array<String>) {
    runApplication<ChargingTransactionServiceApplication>(*args)
}
