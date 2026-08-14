package com.auth.dtos;

import com.auth.entities.Provider;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserRequestDto {

    private String name;
    private String email;
    private String password;
    private String image;
    private boolean enabled= true;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
    private String gender;
    private Provider provider=Provider.LOCAL;
    private Set<RoleDto> roles = new HashSet<>();

}
