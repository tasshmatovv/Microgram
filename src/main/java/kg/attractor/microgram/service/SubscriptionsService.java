package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.PostDto;

import java.util.List;

public interface SubscriptionsService {
    List<PostDto> getPublicFeed();

    List<Integer> getFollowedUserIds(Integer userId);

    boolean isFollowing(Integer followerId, Integer followedId);

    List<PostDto> getPersonalizedFeed(Integer userId);
}
