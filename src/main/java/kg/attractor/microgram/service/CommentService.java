package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.CommentDto;

import java.util.List;

public interface CommentService {
    void addComment(CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(Integer postId);

    Integer getCommentCount(Integer postId);
}
