package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.PostDto;

import java.util.List;

public interface SubscriptionsService {
    List<PostDto> getPublicFeed();

    Integer countByFollowerId(Integer followerId);

    Integer countByFollowedId(Integer followedId);

    List<Integer> getFollowedUserIds(Integer userId);

    void follow(Integer followerId, Integer followedId);

    boolean isFollowing(Integer followerId, Integer followedId);

    List<PostDto> getPersonalizedFeed(Integer userId);

    void unfollow(Integer followerId, Integer followedId);
}
