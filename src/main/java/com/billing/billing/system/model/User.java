package com.billing.billing.system.model;

import com.billing.billing.system.domain.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private  String fullName;
    @Column(nullable = false, unique = true)
    @Email(message = "email should be valid")
    private  String email;
    private  String phone;

    @Column(nullable = false)
    private  String password;

    @Column(nullable = false)
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
}
