package kg.attractor.microgram.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nickName;
    private String email;
    private String password;
    private String fullName;

    @Column(name = "avatarUrl")
    private String avatar;

    private String bio;

    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "accountTypeId")
    private AccountTypeModel accountType;

    @OneToMany(mappedBy = "user")
    private List<PostModel> post;

}
