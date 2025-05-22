package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    void registerUser(UserDto userDto, String avatar);

    UserDto getUserByEmail(String email);

    UserDto getUserById(Integer userId);

    void updateUserAvatar(String email, MultipartFile avatar, String existingAvatar);

    String addAvatar(MultipartFile file);
}
