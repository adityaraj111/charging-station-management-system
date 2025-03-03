package org.aditya.chargingtransactionservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.aditya.chargingtransactionservice.infrastructure.consumer.AuthResponseConsumer
import org.aditya.chargingtransactionservice.infrastructure.producer.AuthorizationProducer
import org.aditya.chargingtransactionservice.model.AuthorizationRequest
import org.aditya.chargingtransactionservice.model.AuthorizationResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class AuthorizationController(
    private val authorizationProducer: AuthorizationProducer,
    private val authResponseConsumer: AuthResponseConsumer,
    @Value("\${kafka.producer.topic}") private var topic : String,
    private val objectMapper: ObjectMapper
) {

    @PostMapping("transaction/authorize")
    fun authorize(@RequestBody request: AuthorizationRequest): AuthorizationResponse {
        val payload = objectMapper.writeValueAsString(request)
        authorizationProducer.publishAuthorizationRequest(topic, request.driverIdentifier.id, payload)
        val authResponseFuture = authResponseConsumer.waitForResponse(request.driverIdentifier.id)
        return authResponseFuture.join()
    }
}