package com.auth.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", unique = true, nullable = false)
    private UUID id;
    @Column(name = "user_name", length = 50, nullable = false)
    private String name;
    @Column(name = "user_email", unique = true, nullable = false, length = 50)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "user_image")
    private String image;
    private boolean enabled= true;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
    @Column(name = "user_gender")
    private String gender;

    @Enumerated(EnumType.STRING)
    private Provider provider=Provider.LOCAL;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        if (createdAt == null) {
            createdAt = now;
        }
        updatedAt = now;

    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }


}
