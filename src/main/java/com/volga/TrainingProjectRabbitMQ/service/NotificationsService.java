package com.volga.TrainingProjectRabbitMQ.service;

import com.volga.TrainingProjectRabbitMQ.config.RabbitMQConfig;
import com.volga.TrainingProjectRabbitMQ.domain.SubscriberNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationsService {

    @RabbitListener(queues = "уведомления_подписчикам")
    public void sendNotification(SubscriberNotification notification) {
        log.info("Получено сообщение из очереди ");
        sendNotificationToUser(notification);
    }

    private void sendNotificationToUser(SubscriberNotification notification) {
        log.info("Отправляем уведомления пользователям {}", notification.getPublicationNotification());
    }
}
