package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.PostDto;
import org.springframework.security.core.Authentication;

public interface PostService {
    void createPost(PostDto postDto, Authentication authentication);
}
