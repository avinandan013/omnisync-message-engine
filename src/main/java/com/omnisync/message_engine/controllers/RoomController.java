package com.omnisync.message_engine.controllers;

import com.omnisync.message_engine.entity.Message;
import com.omnisync.message_engine.entity.Room;
import com.omnisync.message_engine.repositories.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    //? Create Room
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomName) {
        if(roomRepository.findByRoomName(roomName) != null){
            //! Room exists already
            return ResponseEntity.badRequest().body("Room already exists");
        }
        //* create new room
        Room room = new Room();
        room.setRoomName(roomName);
        Room savedRoom = roomRepository.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoom);
    }

    //* get Room : join
    @GetMapping("/{roomName}")
    public ResponseEntity<?> joinRoom(
            @PathVariable String roomName
    ) {
        Room room = roomRepository.findByRoomName(roomName);
        if(room == null){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(room);
        }
    }

    //! get message from Room

    @GetMapping("/{roomName}/messages")
    public ResponseEntity<List<Message>> getMessages(
            @PathVariable String roomName
    ){
        Room room = roomRepository.findByRoomName(roomName);
        if(room == null){
            return ResponseEntity.badRequest().build();
        }
        //get messages
        //!pagination
        return ResponseEntity.ok(room.getMessages());
    }




}
