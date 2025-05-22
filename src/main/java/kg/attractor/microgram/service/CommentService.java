package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.CommentDto;
import kg.attractor.microgram.dto.UserDto;

import java.util.List;

public interface CommentService {
    void addComment(CommentDto commentDto);

    void deleteCommentIfPostOwnedBy(Integer commentId, Integer postId, UserDto currentUser);

    List<CommentDto> getCommentsByPostId(Integer postId);

    void deleteOwnComment(Integer commentId, Integer userId);

    Integer getCommentCount(Integer postId);
}
