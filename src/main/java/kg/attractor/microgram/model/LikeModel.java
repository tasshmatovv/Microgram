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
@Table(name = "likes")
public class LikeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostModel post;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
    @Column(name = "liked_at")
    private LocalDateTime createdAt;
}
