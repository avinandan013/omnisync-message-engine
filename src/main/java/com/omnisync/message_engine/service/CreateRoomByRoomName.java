package com.omnisync.message_engine.service;

import com.omnisync.message_engine.entity.Room;
import com.omnisync.message_engine.repositories.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateRoomByRoomName{

    private RoomRepository roomRepository;

    public CreateRoomByRoomName(RoomRepository roomRepository)
    {
        this.roomRepository = roomRepository;
    }

    public String roomCreation(String roomName){
        if(roomRepository.findByRoomName(roomName) != null){
            return "Room already exists";
        }
        Room room = new Room();
        room.setRoomName(roomName);
        roomRepository.save(room);
        return "Room created successfully";
    }
}
