package kg.attractor.microgram.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeDto {
    private Integer id;
    private PostDto postId;
    private UserDto userId;
    private LocalDateTime createdAt;
}
