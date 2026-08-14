package com.auth.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RoleDto {

    private UUID id;
    private String role;
}
