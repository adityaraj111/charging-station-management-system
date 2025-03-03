package org.aditya.chargingauthenticationservice.infrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import org.aditya.chargingauthenticationservice.model.AuthorizationRequest
import org.aditya.chargingauthenticationservice.service.AuthenticationService
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class AuthRequestConsumer(
    private val authenticationService: AuthenticationService,
    private val authResponseProducer: AuthResponseProducer,
    private val objectMapper: ObjectMapper,
    @Value("\${kafka.producer.topic}") private var topic : String
) {

    @KafkaListener(topics = ["\${kafka.consumer.topic}"], groupId = "charging-authentication-service")
    fun listen(record: ConsumerRecord<String, String>,  ack: Acknowledgment) {
        val authRequest = objectMapper.readValue(record.value(), AuthorizationRequest::class.java)
        val response = authenticationService.authorize(authRequest)
        authResponseProducer.sendAuthorizationResponse(
            topic,
            record.key(),
            objectMapper.writeValueAsString(response)
        )
        ack.acknowledge()
    }
}