package kg.attractor.microgram.service;

public interface LikeService {
    Boolean toggleLike(Integer postId, Integer userId);

    boolean isLikedByUser(Integer postId, Integer userId);

    Integer countLikes(Integer postId);
}
