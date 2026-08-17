package com.omnisync.message_engine.service;
import com.omnisync.message_engine.repositories.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class JoinRoomByRoomName{

    private RoomRepository roomRepository;

    public JoinRoomByRoomName(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public String joinRoom(String roomName) {
        if(roomRepository.findByRoomName(roomName) != null){
            return "Joining you to the room";
        }
        else {
            return "Room not found";
        }
    }
}