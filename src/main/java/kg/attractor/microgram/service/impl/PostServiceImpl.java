package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.Util.FileUtil;
import kg.attractor.microgram.dto.PostDto;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.model.PostModel;
import kg.attractor.microgram.model.UserModel;
import kg.attractor.microgram.repository.PostRepository;
import kg.attractor.microgram.service.CommentService;
import kg.attractor.microgram.service.PostService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final UserService userService;
    private final FileUtil fileUtil;
    private final CommentService commentService;

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

    @Override
    public Integer getPostCount(Integer userId) {
        return postRepository.countByUserId(userId);
    }

    @Override
    public List<PostDto> getPostsByUserId(Integer userId) {
        return postRepository.findAllByUserId(userId)
                .stream()
                .map(postModel -> PostDto.builder()
                        .id(postModel.getId())
                        .userId(postModel.getUser().getId())
                        .imageUrlString(postModel.getImageUrl())
                        .description(postModel.getDescription())
                        .likes(postModel.getLikes())
                        .comments(postModel.getComments())
                        .createdAt(postModel.getCreatedAt().toString())
                        .build())
                .toList();
    }

    @Override
    public ResponseEntity<InputStreamResource> getPostImage(String postImage) {
        return fileUtil.getOutputFile(postImage, "postImages");
    }

    @Override
    public PostDto getPostById(Integer postId) {
        PostModel postModel = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        PostDto postDto = modelMapper.map(postModel, PostDto.class);

        if (postModel.getUser() != null) {
            UserDto userDto = modelMapper.map(postModel.getUser(), UserDto.class);
            userDto.setAvatarUrl(postModel.getUser().getAvatar());
            postDto.setUser(userDto);
        }

        postDto.setComments(commentService.getCommentCount(postId));

        return postDto;
    }

    @Override
    public void updateCommentCount(Integer postId) {
        PostModel post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setComments(commentService.getCommentCount(postId));
        postRepository.save(post);
    }

    private UserModel convertToUserModel(UserDto userDto) {
        return modelMapper.map(userDto, UserModel.class);
    }


    @Override
    public List<PostDto> getAllPosts() {
        List<PostModel> post = postRepository.findAll();
        return post.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    private PostDto convertToDto(PostModel postModel) {
        return modelMapper.map(postModel, PostDto.class);
    }
}
