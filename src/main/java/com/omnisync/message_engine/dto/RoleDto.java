package com.omnisync.message_engine.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RoleDto {
    private UUID id;
    private String roleName;
}
