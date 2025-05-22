package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.dto.PostDto;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.model.PostModel;
import kg.attractor.microgram.model.SubscriptionModel;
import kg.attractor.microgram.model.UserModel;
import kg.attractor.microgram.repository.PostRepository;
import kg.attractor.microgram.repository.SubscriptionRepository;
import kg.attractor.microgram.service.PostService;
import kg.attractor.microgram.service.SubscriptionsService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public List<PostDto> getPublicFeed() {
        List<PostModel> allPosts = postRepository.findAll();
        Collections.shuffle(allPosts);
        return allPosts.stream()
                .map(post -> postService.getPostById(post.getId()))
                .toList();
    }

    @Override
    public Integer countByFollowerId(Integer followerId) {
        return subscriptionRepository.countByFollower_Id(followerId);
    }

    @Override
    public Integer countByFollowedId(Integer followedId) {
        return subscriptionRepository.countByFollowed_Id(followedId);
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


    @Override
    public void follow(Integer followerId, Integer followedId) {
        if (subscriptionRepository.existsByFollowerIdAndFollowedId(followerId, followedId)) {
            throw new IllegalStateException("Вы уже подписаны на этого пользователя");
        }

        UserDto follower = userService.getUserById(followerId);
        UserDto followed = userService.getUserById(followedId);

        SubscriptionModel subscription = SubscriptionModel.builder()
                .follower(modelMapper.map(follower, UserModel.class))
                .followed(modelMapper.map(followed, UserModel.class))
                .build();

        subscriptionRepository.save(subscription);
    }

    @Override
    public void unfollow(Integer followerId, Integer followedId) {
        SubscriptionModel subscription = subscriptionRepository
                .findByFollowerIdAndFollowedId(followerId, followedId)
                .orElseThrow(() -> new RuntimeException("Подписка не найдена"));

        subscriptionRepository.delete(subscription);
    }

}
