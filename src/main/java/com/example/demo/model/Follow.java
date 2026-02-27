package com.example.demo.model;

import java.time.Instant;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "follows",
    uniqueConstraints = @UniqueConstraint(name = "uk_follow_pair", columnNames = {"follower_id","following_id"})
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Follow {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "following_id", nullable = false)
    private User following;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}