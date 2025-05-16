package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.Util.FileUtil;
import kg.attractor.microgram.dto.PostDto;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.model.PostModel;
import kg.attractor.microgram.model.UserModel;
import kg.attractor.microgram.repository.PostRepository;
import kg.attractor.microgram.service.PostService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final UserService userService;
    private final FileUtil fileUtil;

    @Override
    public void createPost(PostDto postDto, Authentication authentication) {
        UserDto authorDto = userService.getUserByEmail(authentication.getName());
        UserModel authorModel = convertToUserModel(authorDto);

        String fileName = fileUtil.saveUploadFile(postDto.getImageUrl(), "postImages");

        PostModel postModel = new PostModel();
        postModel.setUser(authorModel);
        postModel.setImageUrl(fileName);
        postModel.setDescription(postDto.getDescription());
        postModel.setLikes(0);
        postModel.setComments(0);
        postModel.setCreatedAt(LocalDateTime.now());

        postRepository.save(postModel);
    }



    private UserModel convertToUserModel(UserDto userDto) {
        return modelMapper.map(userDto, UserModel.class);
    }

}
