package kg.attractor.microgram.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Integer id;
    private Integer postId;
    private UserDto user;

    @NotBlank(message = "Комментарий не может быть пустым")
    private String text;
    private LocalDateTime createdAt;
}
