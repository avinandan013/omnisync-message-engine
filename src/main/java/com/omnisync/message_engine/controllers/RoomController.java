package com.omnisync.message_engine.controllers;

import com.omnisync.message_engine.Service.CreateRoom;
import com.omnisync.message_engine.Service.GetMessages;
import com.omnisync.message_engine.Service.JoinRoom;
import com.omnisync.message_engine.entity.Message;
import com.omnisync.message_engine.entity.Room;
import com.omnisync.message_engine.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private RoomRepository roomRepository;
    @Autowired
    private CreateRoom createRoom;
    @Autowired
    private JoinRoom joinRoom;
    @Autowired
    private GetMessages getMessages;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public RoomController(CreateRoom createRoom) { this.createRoom = createRoom;}

    public RoomController(JoinRoom joinRoom) {this.joinRoom = joinRoom;}

    public RoomController(GetMessages getMessages) {this.getMessages = getMessages;}

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
