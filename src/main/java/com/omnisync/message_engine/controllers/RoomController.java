package com.omnisync.message_engine.controllers;

import com.omnisync.message_engine.Service.CreateRoom;
import com.omnisync.message_engine.Service.GetMessages;
import com.omnisync.message_engine.Service.JoinRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
@CrossOrigin("*")
public class RoomController {

    @Autowired
    private CreateRoom createRoom;
    @Autowired
    private JoinRoom joinRoom;
    @Autowired
    private GetMessages getMessages;

    //? Create Room
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomName) {
        return createRoom.roomCreation(roomName);
    }

    //* get Room : join
    @GetMapping("/{roomName}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomName) {
        return joinRoom.roomJoin(roomName);
    }

    //! get message from Room

    @GetMapping("/{roomName}/messages")
    public ResponseEntity<?> getMessages(@PathVariable String roomName){
        return getMessages.getMessagesFromRoom(roomName);
    }

}
