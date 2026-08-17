package com.omnisync.message_engine.service;

import com.omnisync.message_engine.entity.Message;
import com.omnisync.message_engine.entity.Room;
import com.omnisync.message_engine.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetMessagesByRoomName{
    @Autowired
    private RoomRepository roomRepository;


    public List<Message> getMessagesByRoomName (String roomName){
        Room room = roomRepository.findByRoomName(roomName);
        if(room == null){
            return null;
        }else {
            return room.getMessages();
        }
    }
}
