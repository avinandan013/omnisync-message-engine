package com.omnisync.message_engine.Service;

import com.omnisync.message_engine.entity.Room;
import com.omnisync.message_engine.repositories.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CreateRoom {

    private RoomRepository roomRepository;

    public CreateRoom(RoomRepository roomRepository){this.roomRepository = roomRepository;}

    public ResponseEntity<?> roomCreation(String roomName){
        if(roomRepository.findByRoomName(roomName) != null){
            return ResponseEntity.badRequest().body("Room exists already");
        }

        Room room = new Room();
        room.setRoomName(roomName);
        roomRepository.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }
}
