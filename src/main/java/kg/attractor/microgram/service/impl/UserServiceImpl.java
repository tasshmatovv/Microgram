package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.Util.FileUtil;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.exceptions.EmailAlreadyExistsException;
import kg.attractor.microgram.exceptions.NickAlreadyExistsException;
import kg.attractor.microgram.exceptions.UserNotFoundException;
import kg.attractor.microgram.model.UserModel;
import kg.attractor.microgram.repository.UserRepository;
import kg.attractor.microgram.service.AccountTypeService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Lazy))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountTypeService accountTypeService;
    private final FileUtil fileUtil;
    private ModelMapper modelMapper = new ModelMapper();

    private final SubscriptionsServiceImpl subscriptionService;

    @Override
    public List<Integer> getFollowedUserIds(Integer userId) {
        return subscriptionService.getFollowedUserIds(userId);
    }

    @Override
    public void registerUser(UserDto userDto, String avatar) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EmailAlreadyExistsException("Пользователь с такой почтой уже существует");
        }

        if (userRepository.existsByNickName(userDto.getNickName())) {
            throw new NickAlreadyExistsException("Пользователь с таким логином уже существует");
        }

        String avatarFileName = "noProfilePhoto.jpg";
        if (!avatar.isEmpty()) {
            avatarFileName = fileUtil.saveUploadFile(userDto.getAvatar(), "avatars");
        }

        UserModel user = UserModel.builder()
                .nickName(userDto.getNickName())
                .fullName(userDto.getFullName())
                .bio(userDto.getBio())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .accountType(accountTypeService.getUserAccountType())
                .enabled(true)
                .avatar(avatarFileName)
                .build();

        userRepository.save(user);
    }

    @Override
    public List<UserDto> getSuggestedUsersForSubscription(Integer userId) {
        List<Integer> followedUserIds = getFollowedUserIds(userId);
        List<UserModel> allUsers = userRepository.findAll();
        return allUsers.stream()
                .filter(user -> !user.getId().equals(userId) && !followedUserIds.contains(user.getId()))
                .map(this::convertToDto)
                .toList();
    }


    @Override
    public UserDto getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(this::convertToDto)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с email: " + email));
    }

    @Override
    public UserDto getUserById(Integer userId) {
        return userRepository.findById(userId)
                .map(this::convertToDto)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + userId));
    }

    @Override
    public void updateUserAvatar(String email, MultipartFile avatar, String existingAvatar) {
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String avatarFileName;
        if (avatar != null && !avatar.isEmpty()) {
            avatarFileName = addAvatar(avatar);
        } else {
            avatarFileName = existingAvatar;
        }

        user.setAvatar(avatarFileName);
        userRepository.save(user);
    }

    @Override
    public String addAvatar(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Avatar file is empty");
        }
        String fileName = fileUtil.saveUploadFile(file, "avatars");
        return fileName;
    }

    private UserDto convertToDto(UserModel user) {
        UserDto dto = modelMapper.map(user, UserDto.class);
        dto.setAvatarUrl(user.getAvatar());
        return dto;
    }


    @Override
    public List<UserDto> findUserByNickNameOrName(String query) {
        List<UserModel> userModels = userRepository.findUserByNameOrNickName(query);

        return userModels.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    @Override
    public UserModel findById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
