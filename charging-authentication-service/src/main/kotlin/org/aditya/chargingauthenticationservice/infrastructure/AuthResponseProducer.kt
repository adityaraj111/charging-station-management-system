package org.aditya.chargingauthenticationservice.infrastructure

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class AuthResponseProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    fun sendAuthorizationResponse(topic: String, key: String, response: String) {
        kafkaTemplate.send(topic, key, response)
    }
}