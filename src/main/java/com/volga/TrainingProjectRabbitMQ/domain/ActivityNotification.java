package com.volga.TrainingProjectRabbitMQ.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityNotification implements Serializable {

    private String userId;
    private String activityType;
    private String details;
}
