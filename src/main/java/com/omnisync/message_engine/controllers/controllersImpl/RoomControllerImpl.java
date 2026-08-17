package com.omnisync.message_engine.controllers.controllersImpl;

import com.omnisync.message_engine.entity.Message;
import com.omnisync.message_engine.service.CreateRoomByRoomName;
import com.omnisync.message_engine.service.GetMessagesByRoomName;
import com.omnisync.message_engine.service.JoinRoomByRoomName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomControllerImpl {
    @Autowired
    private CreateRoomByRoomName createRoomByRoomName;
    @Autowired
    private JoinRoomByRoomName joinRoomByRoomName;
    @Autowired
    private GetMessagesByRoomName getMessagesByRoomName;

    public ResponseEntity<?> roomCreation(String roomName){
        String result = createRoomByRoomName.roomCreation(roomName);
        if(!result.isEmpty() && result.equals("Room already exists")){
            return ResponseEntity.badRequest().body(result);
        }else if (!result.isEmpty() && result.equals("Room created successfully")){
            return ResponseEntity.ok().body(result);
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    public ResponseEntity<?> roomJoin(String roomName){
        String result = joinRoomByRoomName.joinRoom(roomName);
        if(!result.isEmpty() && result.equals("Joining you to the room")){
            return ResponseEntity.ok().build();
        }
        else if (!result.isEmpty() && result.equals("Room not found")){
            return ResponseEntity.badRequest().body(result.toString());
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    public ResponseEntity<?> getMessage(String roomName) {
        List<Message> result = getMessagesByRoomName.getMessagesByRoomName(roomName);
        if(result.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }
}
