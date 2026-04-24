package kani.springsecurity.Domain.Embeding;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

@Data
@Entity
@Table(name = "profile_embeddings")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Embeding {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user_id;

    @Column(columnDefinition = "vector(1536)")
    private float[] embedding ;
}
