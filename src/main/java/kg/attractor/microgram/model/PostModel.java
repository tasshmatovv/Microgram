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
@Table(name = "posts")
public class PostModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserModel user;

    private String imageUrl;

    private String description;

    @Column(name = "likeCount")
    private Integer likes;

    @Column(name = "commentCount")
    private Integer comments;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;
}
