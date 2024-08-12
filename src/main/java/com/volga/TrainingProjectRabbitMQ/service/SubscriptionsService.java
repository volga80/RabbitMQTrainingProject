package com.volga.TrainingProjectRabbitMQ.service;

import com.volga.TrainingProjectRabbitMQ.config.RabbitMQConfig;
import com.volga.TrainingProjectRabbitMQ.domain.PublicationNotification;
import com.volga.TrainingProjectRabbitMQ.domain.SubscriberNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SubscriptionsService {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitAdmin rabbitAdmin;
    private final TopicExchange exchange;

    @Autowired
    public SubscriptionsService(RabbitTemplate rabbitTemplate, RabbitAdmin rabbitAdmin, TopicExchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitAdmin = rabbitAdmin;
        this.exchange = exchange;
    }

    @RabbitListener(queues = "новые_публикации")
    public void handleNewPublication(PublicationNotification notification) {
        log.info("Получено уведомление из rabbit для рассылки в очереди подписчиков");
//        List<String> subscribers = getSubscribers(notification);
//        for (String subscriber : subscribers) {
//            String subscriberQueue = "уведомления_" + subscriber;
//            if (rabbitAdmin.getQueueProperties(subscriberQueue) == null) {
//                createQueueForSubscriber(subscriberQueue);
//                log.info("Создана очередь {}", subscriberQueue);
//            }
            SubscriberNotification subscriberNotification = new SubscriberNotification();
            subscriberNotification.setPublicationNotification(notification);
            subscriberNotification.setSubscriberId(notification.getUserId());
            rabbitTemplate.convertAndSend(RabbitMQConfig.SUBSCRIPTION_QUEUE, subscriberNotification);
            log.info("Успешная отправка по очередям");
        }
    }

//    private void createQueueForSubscriber(String queueName) {
//        Queue queue = new Queue(queueName, true);
//        rabbitAdmin.declareQueue(queue);
//
//        Binding binding = BindingBuilder.bind(queue).to(exchange).with(queueName);
//        rabbitAdmin.declareBinding(binding);
//    }
//
//    private List<String> getSubscribers(PublicationNotification notification) {
//        return List.of("Подписчик1", "Подписчик2",
//                "Подписчик3", "Подписчик4");
//    }
//}
