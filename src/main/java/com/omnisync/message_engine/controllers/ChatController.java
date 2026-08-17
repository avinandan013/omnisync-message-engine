package com.omnisync.message_engine.controllers;

import com.omnisync.message_engine.controllers.controllersImpl.ChatControllerImpl;
import com.omnisync.message_engine.entity.Message;
import com.omnisync.message_engine.entity.Room;
import com.omnisync.message_engine.payload.MessageRequest;
import com.omnisync.message_engine.repositories.RoomRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Controller
@CrossOrigin("*")
@Slf4j
public class ChatController {

    @Autowired
    private ChatControllerImpl chatControllerImpl;

    //? For sending and receiving messages
    @MessageMapping("/sendMessage/{roomId}")  //!   /app/sendMessage/roomId
    @SendTo("/topic/room/{roomId}") //!  subscribe
    public Message sendMessage(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest messageRequest)
    {
        log.info("WebSocket message received");
        log.info("Room     : {}", roomId);
        log.info("Sender   : {}", messageRequest.getSender());
        log.info("Content  : {}", messageRequest.getContent());

        return chatControllerImpl.send(roomId, messageRequest);
    }
}
