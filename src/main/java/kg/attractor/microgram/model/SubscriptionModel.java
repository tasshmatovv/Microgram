package kg.attractor.microgram.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "followers")
public class SubscriptionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "id")
    private UserModel follower;

    @ManyToOne
    @JoinColumn(name = "followed_id", referencedColumnName = "id")
    private UserModel followed;
}
