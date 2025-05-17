package kg.attractor.microgram.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class CommentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "postId")
    private PostModel post;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserModel user;

    private String text;

    private LocalDateTime createdAt;

}
