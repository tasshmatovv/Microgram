package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.Util.FileUtil;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.exceptions.UserAlreadyExistsException;
import kg.attractor.microgram.exceptions.UserNotFoundException;
import kg.attractor.microgram.model.UserModel;
import kg.attractor.microgram.repository.UserRepository;
import kg.attractor.microgram.service.AccountTypeService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountTypeService accountTypeService;
    private final FileUtil fileUtil;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public void registerUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistsException("Пользователь с такой почтой уже существует");
        }

        if (userRepository.existsByNickName(userDto.getNickName())) {
            throw new UserAlreadyExistsException("Пользователь с таким логином уже существует");
        }

        String avatarFileName = "noProfilePhoto.jpg";
        MultipartFile avatarFile = userDto.getAvatar();
        if (avatarFile != null && !avatarFile.isEmpty()) {
            avatarFileName = fileUtil.saveUploadFile(avatarFile, "avatars");
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
    public UserDto getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(this::convertToDto)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с email: " + email));
    }

    private UserDto convertToDto(UserModel user) {
        return modelMapper.map(user, UserDto.class);
    }
}
