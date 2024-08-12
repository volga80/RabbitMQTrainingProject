package com.volga.TrainingProjectRabbitMQ.service;

import com.volga.TrainingProjectRabbitMQ.config.RabbitMQConfig;
import com.volga.TrainingProjectRabbitMQ.domain.ActivityNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ActivityService {

    @RabbitListener(queues = {RabbitMQConfig.LIKE_QUEUE, RabbitMQConfig.COMMENT_QUEUE})
    public void handleActivity(ActivityNotification notification) {
        log.info("Получено уведомление из кролика о новом действии");
        sendActivity(notification);
    }

    private void sendActivity(ActivityNotification notification) {
        log.info("Отправляет уведомления пользователям об активости {}",
                notification.getActivityType());
    }
}

