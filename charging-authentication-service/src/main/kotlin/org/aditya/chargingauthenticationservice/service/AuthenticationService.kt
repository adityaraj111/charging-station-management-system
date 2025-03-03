package org.aditya.chargingauthenticationservice.service

import org.aditya.chargingauthenticationservice.model.AuthorizationRequest
import org.aditya.chargingauthenticationservice.model.AuthorizationResponse
import org.springframework.stereotype.Service

@Service
class AuthenticationService {

    private val whiteList = setOf("cms123489047893090989826783", "cms5678749373763876123")
    private val rejectList = setOf("cms9999489047893090989826783")

    fun authorize(request: AuthorizationRequest): AuthorizationResponse {
        val id = request.driverIdentifier.id

        return AuthorizationResponse(
            authorizationStatus = when {
                !isValidIdentifier(id) -> "Invalid"
                rejectList.contains(id) -> "Rejected"
                !whiteList.contains(id) -> "Unknown"
                whiteList.contains(id) -> "Accepted"
                else -> "Rejected"
            }
        )
    }

    private fun isValidIdentifier(id: String): Boolean {
        return id.length in 20..80
    }
}