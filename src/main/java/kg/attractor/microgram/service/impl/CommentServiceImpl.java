package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.dto.CommentDto;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.model.CommentModel;
import kg.attractor.microgram.model.PostModel;
import kg.attractor.microgram.repository.CommentRepository;
import kg.attractor.microgram.repository.PostRepository;
import kg.attractor.microgram.service.CommentService;
import kg.attractor.microgram.service.PostService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final UserService userService;
    private final PostRepository postRepository;
    private final PostService postService;

    @Override
    public void addComment(CommentDto commentDto) {
        CommentModel comment = modelMapper.map(commentDto, CommentModel.class);
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
        postService.updateCommentCount(commentDto.getPostId());

    }

    @Override
    public void deleteCommentIfPostOwnedBy(Integer commentId, Integer postId, UserDto currentUser) {
        PostModel post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Пост не найден"));

        if (!post.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Вы не можете удалять комментарии под чужими постами");
        }

        commentRepository.deleteById(commentId);
        postService.updateCommentCount(postId);

    }

    @Override
    public List<CommentDto> getCommentsByPostId(Integer postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(comment -> {
                    CommentDto dto = modelMapper.map(comment, CommentDto.class);
                    dto.setUser(userService.getUserById(comment.getUser().getId()));
                    dto.getUser().setAvatarUrl("/profile/avatars/" + dto.getUser().getAvatarUrl());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOwnComment(Integer commentId, Integer userId) {
        CommentModel comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Комментарий не найден"));

        if (!comment.getUser().getId().equals(userId)) {
            throw new RuntimeException("Вы не можете удалять чужие комментарии");
        }

        commentRepository.deleteById(commentId);
        postService.updateCommentCount(comment.getPost().getId());
    }


    @Override
    public Integer getCommentCount(Integer postId) {
        return commentRepository.countByPostId(postId);
    }
}
