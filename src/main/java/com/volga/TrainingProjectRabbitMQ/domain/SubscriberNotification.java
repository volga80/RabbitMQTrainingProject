package com.volga.TrainingProjectRabbitMQ.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberNotification implements Serializable {
    private String subscriberId;
    private PublicationNotification publicationNotification;
}
