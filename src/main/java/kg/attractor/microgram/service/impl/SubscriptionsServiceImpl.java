package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.dto.PostDto;
import kg.attractor.microgram.model.PostModel;
import kg.attractor.microgram.repository.PostRepository;
import kg.attractor.microgram.repository.SubscriptionRepository;
import kg.attractor.microgram.service.PostService;
import kg.attractor.microgram.service.SubscriptionsService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionsServiceImpl implements SubscriptionsService {


    private final PostRepository postRepository;
    private final PostService postService;
    private final UserService userService;
    private final SubscriptionRepository subscriptionRepository;


    @Override
    public List<PostDto> getPublicFeed() {
        List<PostModel> allPosts = postRepository.findAll();
        Collections.shuffle(allPosts);
        return allPosts.stream()
                .map(post -> postService.getPostById(post.getId()))
                .toList();
    }
    @Override
    public List<Integer> getFollowedUserIds(Integer userId) {
        return subscriptionRepository.findFollowedUserIdsByFollowerId(userId);
    }

    @Override
    public boolean isFollowing(Integer followerId, Integer followedId) {
        return subscriptionRepository.existsByFollowerIdAndFollowedId(followerId, followedId);
    }

    @Override
    public List<PostDto> getPersonalizedFeed(Integer userId) {
        List<Integer> followedUserIds = userService.getFollowedUserIds(userId);

        if (followedUserIds.isEmpty()) {
            return List.of();
        }

        List<PostModel> followedPosts = postRepository.findAllByUserIdIn(followedUserIds);
        Collections.shuffle(followedPosts);
        return followedPosts.stream()
                .map(post -> postService.getPostById(post.getId()))
                .toList();
    }

}
