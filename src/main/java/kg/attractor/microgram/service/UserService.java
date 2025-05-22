package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.UserDto;

public interface UserService {

    void registerUser(UserDto userDto, String avatar);

    UserDto getUserByEmail(String email);

    UserDto getUserById(Integer userId);
}
