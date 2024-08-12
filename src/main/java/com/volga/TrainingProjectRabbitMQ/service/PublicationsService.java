package com.volga.TrainingProjectRabbitMQ.service;

import com.volga.TrainingProjectRabbitMQ.domain.PublicationNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PublicationsService {
    private final RabbitTemplate rabbitTemplate;

    public PublicationsService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void createPublication(String postContent, String userId) {
        log.info("Опубликован новый пост, {}", postContent);
        PublicationNotification notification = new PublicationNotification(userId, postContent);
        rabbitTemplate.convertAndSend("новые_публикации", notification);
        log.info("Публикация отправляется в кролик {}", notification);
    }
}

