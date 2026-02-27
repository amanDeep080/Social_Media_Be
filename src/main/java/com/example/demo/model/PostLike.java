package com.example.demo.model;

import java.time.Instant;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "post_likes",
    uniqueConstraints = @UniqueConstraint(name = "uk_like_post_user", columnNames = {"post_id","user_id"})
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PostLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}