package com.omnisync.message_engine.service;

import com.omnisync.message_engine.dto.UserDto;

public interface UserService {

    //! create User
    UserDto createUser(UserDto userDto);

    //! get user by email
    UserDto getUserByEmail(String email);

    //! update user
    UserDto updateUser(UserDto userDto, String userId);

    //! delete user
    void deleteUser(String userId);

    //! get user by the Id
    UserDto getUserById(String userId);


    //! get all the users
    Iterable<UserDto> getAllUsers();
}
