package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.model.LikeModel;
import kg.attractor.microgram.model.PostModel;
import kg.attractor.microgram.model.UserModel;
import kg.attractor.microgram.repository.LikeRepository;
import kg.attractor.microgram.service.LikeService;
import kg.attractor.microgram.service.PostService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final PostService postService;
    private final LikeRepository likeRepository;
    private final UserService userService;


    @Override
    public Boolean toggleLike(Integer postId, Integer userId) {
        PostModel post = postService.getPostModelById(postId);
        UserModel user = userService.findById(userId);

        Optional<LikeModel> existingLike = likeRepository.findByPostAndUser(post.getId(), user.getId());
        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            return false;
        } else {
            LikeModel newLike = new LikeModel();
            newLike.setPost(post);
            newLike.setUser(user);
            newLike.setCreatedAt(LocalDateTime.now());
            likeRepository.save(newLike);
            return true;
        }
    }

    @Override
    public boolean isLikedByUser(Integer postId, Integer userId) {
        return likeRepository.existsByPost_IdAndUser_Id(postId, userId);
    }



    @Override
    public Integer countLikes(Integer postId) {
        return likeRepository.countByPost(postId);
    }

}
