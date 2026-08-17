package com.omnisync.message_engine.service;

import com.omnisync.message_engine.entity.Message;
import com.omnisync.message_engine.entity.Room;
import com.omnisync.message_engine.payload.MessageRequest;
import com.omnisync.message_engine.repositories.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class SendMessages {
    @Autowired
    RoomRepository roomRepository;
    public Message SendMessages(String roomId, MessageRequest messageRequest) throws Exception {
        Room room = roomRepository.findByRoomName(roomId);

        if (room == null) {
            log.warn("Room not found: {}", roomId);
            throw new RuntimeException("Room not found");
        }

        Message message = new Message();
        message.setContent(messageRequest.getContent());
        message.setSender(messageRequest.getSender());
        message.setTimestamp(LocalDateTime.now());

        if(room != null) {
            room.getMessages().add(message);
            roomRepository.save(room);
            log.info("Message saved to room: {}", roomId);
            log.info("Broadcasting message to: /topic/room/{}", roomId);
        }else{
            throw new RuntimeException("Room not found");
        }
        return message;
    }
}
