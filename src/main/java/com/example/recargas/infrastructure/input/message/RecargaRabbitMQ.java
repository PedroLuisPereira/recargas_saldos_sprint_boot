package com.example.recargas.infrastructure.input.message;

import com.example.recargas.application.recarga.comando.RecargaVender;
import com.example.recargas.application.recarga.dto.RecargaCrearDto;
import com.example.recargas.domain.dto.CompraDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Recibir una compra json y procesar
 */
@Service
public class RecargaRabbitMQ {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecargaRabbitMQ.class);
    private final RecargaVender recargaVender;

    public RecargaRabbitMQ(RecargaVender recargaVender) {
        this.recargaVender = recargaVender;
    }

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(CompraDto compraDto){
        LOGGER.info("Received JSON message -> {}", compraDto);

        recargaVender.ejecutar( new RecargaCrearDto(compraDto.getValor(), compraDto.getOperador()));
    }
}

