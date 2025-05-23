package kg.attractor.microgram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private LocalDateTime createdAt;
    private String imageUrlString;
    private UserDto user;



    public String getFormattedUpdatedTime() {
        if (createdAt == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return createdAt.format(formatter);
    }
}
