package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.PostDto;
import kg.attractor.microgram.model.PostModel;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface PostService {
    void createPost(PostDto postDto, Authentication authentication);

    Integer getPostCount(Integer userId);

    List<PostDto> getPostsByUserId(Integer userId);

    ResponseEntity<InputStreamResource> getPostImage(String postImage);

    PostDto getPostById(Integer postId);

    PostModel getPostModelById(Integer postId);

    void updateCommentCount(Integer postId);

    List<PostDto> getAllPosts();
}
