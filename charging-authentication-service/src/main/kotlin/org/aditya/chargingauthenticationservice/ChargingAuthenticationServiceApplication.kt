package org.aditya.chargingauthenticationservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChargingAuthenticationServiceApplication

fun main(args: Array<String>) {
    runApplication<ChargingAuthenticationServiceApplication>(*args)
}
