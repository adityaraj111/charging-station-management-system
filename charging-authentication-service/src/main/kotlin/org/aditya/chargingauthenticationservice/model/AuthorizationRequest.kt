package org.aditya.chargingauthenticationservice.model

data class AuthorizationRequest(
    val stationUuid: String,
    val driverIdentifier: DriverIdentifier
)