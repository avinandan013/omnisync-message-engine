package com.omnisync.message_engine.controllers;

import com.omnisync.message_engine.entity.Message;
import com.omnisync.message_engine.entity.Room;
import com.omnisync.message_engine.payload.MessageRequest;
import com.omnisync.message_engine.repositories.RoomRepository;
import lombok.Getter;
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
public class ChatController {

    private RoomRepository roomRepository;
    public ChatController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    //? For sending and receiving messages
    @MessageMapping("/sendMessage/{roomId}")  //!   /app/sendMessage/roomId
    @SendTo("topic/room/{roomId}") //!  subscribe
    public Message sendMessage(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest messageRequest)
    {
        Room room = roomRepository.findByRoomName(messageRequest.getRoomId());

        Message message = new Message();
        message.setContent(messageRequest.getContent());
        message.setSender(messageRequest.getSender());
        message.setTimestamp(LocalDateTime.now());

        if(room != null) {
            room.getMessages().add(message);
            roomRepository.save(room);
        }else{
            throw new RuntimeException("Room not found");
        }
        return message;
    }
}
