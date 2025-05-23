package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.model.UserModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    void registerUser(UserDto userDto, String avatar);

    List<UserDto> getSuggestedUsersForSubscription(Integer userId);

    List<Integer> getFollowedUserIds(Integer userId);

    UserDto getUserByEmail(String email);

    UserDto getUserById(Integer userId);

    void updateUserAvatar(String email, MultipartFile avatar, String existingAvatar);

    String addAvatar(MultipartFile file);

    List<UserDto> findUserByNickNameOrName(String query);

    UserModel findById(Integer userId);

    void editUser(UserDto userDto, Integer userId);
}
