package com.kaiwa.notificationservice.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaiwa.notificationservice.event.message.exchange.MessageNotification;
import org.springframework.stereotype.Component;

@Component
public class JsonConverter {

    public MessageNotification jsonToMessageNotification(String jsonMessageNotification)
            throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonMessageNotification, MessageNotification.class);
    }

}
