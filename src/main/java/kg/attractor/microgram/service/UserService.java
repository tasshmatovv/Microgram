package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.UserDto;

public interface UserService {
    void registerUser(UserDto userDto);

    UserDto getUserByEmail(String email);

    UserDto getUserById(Integer userId);
}
