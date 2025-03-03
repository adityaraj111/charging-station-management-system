package org.aditya.chargingtransactionservice.infrastructure.producer

import org.aditya.chargingtransactionservice.model.AuthorizationRequest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class AuthorizationProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    fun publishAuthorizationRequest(topic : String, key : String, payload : String) {
        kafkaTemplate.send(topic, key,  payload)
    }
}