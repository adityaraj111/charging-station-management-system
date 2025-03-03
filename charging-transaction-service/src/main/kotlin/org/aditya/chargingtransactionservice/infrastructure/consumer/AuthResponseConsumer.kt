package org.aditya.chargingtransactionservice.infrastructure.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import org.aditya.chargingtransactionservice.model.AuthorizationResponse
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap

@Component
class AuthResponseConsumer(
    private val objectMapper: ObjectMapper
) {

    private val authCache = ConcurrentHashMap<String, CompletableFuture<AuthorizationResponse>>()

    @KafkaListener(topics = ["\${kafka.consumer.topic}"], groupId = "charging-transaction-service")
    fun listen(record: ConsumerRecord<String, String>,  ack: Acknowledgment) {
        val authResponse = objectMapper.readValue(record.value(), AuthorizationResponse::class.java)
        val future = authCache[record.key()]
        future?.complete(authResponse)
        ack.acknowledge()
    }

    fun waitForResponse(key: String): CompletableFuture<AuthorizationResponse> {
        return authCache.computeIfAbsent(key) { CompletableFuture() }
    }

}