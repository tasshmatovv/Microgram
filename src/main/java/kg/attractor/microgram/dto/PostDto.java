package kg.attractor.microgram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Integer id;
    private Integer userId;
    private MultipartFile imageUrl;
    private String description;
    private Integer likes;
    private Integer comments;
    private String createdAt;
}
