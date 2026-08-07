package com.omnisync.message_engine.repositories;

import com.omnisync.message_engine.entity.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {
    //* get room using roomId (in our case we are naming it as roomName)
    Room findByRoomName(String roomName);
}
