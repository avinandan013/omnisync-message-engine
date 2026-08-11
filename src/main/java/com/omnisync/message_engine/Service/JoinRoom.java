package com.omnisync.message_engine.Service;

import com.omnisync.message_engine.repositories.RoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JoinRoom {
    private RoomRepository roomRepository;

    public JoinRoom(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }
    public ResponseEntity<?> roomJoin(String roomName){
        if(roomRepository.findByRoomName(roomName) != null){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().body("Room not found");
    }
}
