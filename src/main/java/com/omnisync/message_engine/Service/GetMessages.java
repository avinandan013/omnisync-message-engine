package com.omnisync.message_engine.Service;

import com.omnisync.message_engine.entity.Message;
import com.omnisync.message_engine.entity.Room;
import com.omnisync.message_engine.repositories.RoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GetMessages {
    private RoomRepository roomRepository;

    public GetMessages(RoomRepository roomRepository){this.roomRepository = roomRepository;}

    public ResponseEntity<?> getMessagesFromRoom (String roomName){
        if(roomRepository.findByRoomName(roomName) == null){
            return ResponseEntity.badRequest().build();
        }
        Room room = roomRepository.findByRoomName(roomName);
        return ResponseEntity.ok().body(room.getMessages());
    }
}
