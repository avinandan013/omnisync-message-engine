package com.omnisync.message_engine.controllers.controllersImpl;
import com.omnisync.message_engine.entity.Message;
import com.omnisync.message_engine.payload.MessageRequest;
import com.omnisync.message_engine.service.SendMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChatControllerImpl {
    @Autowired
    SendMessages sendMessages;
        public Message send(String roomId, MessageRequest messageRequest){
            try {
                Message result = sendMessages.SendMessages(roomId, messageRequest);
                return result;
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);
            }
            return null;
        }
}
