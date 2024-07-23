package com.kaiwa.messageservice.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaiwa.messageservice.event.notification.exchange.MessageNotificationRequest;
import org.springframework.stereotype.Component;

@Component
public class JsonConverter {

    public String messageNotificationRequestToJson(MessageNotificationRequest messageNotificationRequest)
            throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(messageNotificationRequest);
    }

}
