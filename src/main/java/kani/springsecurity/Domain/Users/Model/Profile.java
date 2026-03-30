package kani.springsecurity.Domain.Users.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_profiles")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {
    @Id
    private Long userId;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId@JoinColumn(name = "user_id")
    private Users user;

    @Column(length = 256)
    private String bio;

    @Column(name = "favorite_animal", length = 256)
    private String favoriteAnimal;

    @Column(name = "magic_place", length = 256)
    private String magicPlace;

    private Integer age;

}
