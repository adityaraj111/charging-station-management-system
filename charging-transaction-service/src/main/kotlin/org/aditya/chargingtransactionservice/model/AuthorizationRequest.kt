package org.aditya.chargingtransactionservice.model

data class AuthorizationRequest(
    val stationUuid: String,
    val driverIdentifier: DriverIdentifier
)