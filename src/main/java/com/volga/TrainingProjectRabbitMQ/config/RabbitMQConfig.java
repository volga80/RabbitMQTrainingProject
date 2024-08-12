package com.volga.TrainingProjectRabbitMQ.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PUBLICATION_QUEUE = "новые_публикации";
    public static final String SUBSCRIPTION_QUEUE = "уведомления_подписчикам";
    public static final String LIKE_QUEUE = "новый_лайк";
    public static final String COMMENT_QUEUE = "новый_комментарий";

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue publicationQueue() {
        return new Queue(PUBLICATION_QUEUE, true);
    }

    @Bean
    public Queue subscriptionQueue() {
        return new Queue(SUBSCRIPTION_QUEUE, true);
    }

    @Bean
    public Queue likeQueue() {
        return new Queue(LIKE_QUEUE, true);
    }

    @Bean
    public Queue commentQueue() {
        return new Queue(COMMENT_QUEUE, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("social_network_exchange");
    }

    @Bean
    public Binding publicationBinding(Queue publicationQueue, TopicExchange exchange) {
        return BindingBuilder.bind(publicationQueue).to(exchange).with(PUBLICATION_QUEUE);
    }

    @Bean
    public Binding subscriptionBinding(Queue subscriptionQueue, TopicExchange exchange) {
        return BindingBuilder.bind(subscriptionQueue).to(exchange).with(SUBSCRIPTION_QUEUE);
    }

    @Bean
    public Binding likeBinding(Queue likeQueue, TopicExchange exchange) {
        return BindingBuilder.bind(likeQueue).to(exchange).with(LIKE_QUEUE);
    }

    @Bean
    public Binding commentBinding(Queue commentQueue, TopicExchange exchange) {
        return BindingBuilder.bind(commentQueue).to(exchange).with(COMMENT_QUEUE);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory,
//                                                                   NotificationsService notificationsService) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames("dynamicQueue");
//        MessageListenerAdapter adapter = new MessageListenerAdapter(notificationsService,
//                "sendNotification");
//        adapter.setMessageConverter(jsonMessageConverter());
//        container.setMessageListener(adapter);
//        container.addQueueNames("уведомления_Подписчик1");
//        container.addQueueNames("уведомления_Подписчик2");
//        container.addQueueNames("уведомления_Подписчик3");
//        container.addQueueNames("уведомления_Подписчик4");
//        return container;
//    }
//
//    @Bean
//    public Queue dynamicQueue() {
//        return new Queue("dynamicQueue", false, true, true);
//    }
}
