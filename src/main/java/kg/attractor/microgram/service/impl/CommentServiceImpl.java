package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.dto.CommentDto;
import kg.attractor.microgram.model.CommentModel;
import kg.attractor.microgram.repository.CommentRepository;
import kg.attractor.microgram.service.CommentService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final UserService userService;

    @Override
    public void addComment(CommentDto commentDto) {
        CommentModel comment = modelMapper.map(commentDto, CommentModel.class);
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Integer postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(comment -> {
                    CommentDto dto = modelMapper.map(comment, CommentDto.class);
                    dto.setUser(userService.getUserById(comment.getUser().getId()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Integer getCommentCount(Integer postId) {
        return commentRepository.countByPostId(postId);
    }
}
