package com.bilalkose.springsecuritybasicauth.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;


@Data
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    // UserDetails implement ediyoruz,yani bizim user sınıfımızı bir referans olarak tanıtıyoruz Cünkü;
    // Security Context desin ki Bu Security Containerında olacak bir class, ben bunu burada tutayım.
    // Bunu yapmamızı da UserDetails org.springframework.security.core.userdetails.UserDetails; sağlıyor.


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;

    private boolean accountNonExpired;
    private boolean isEnabled;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    //bir userın aldığı tüm rolları ayrı bir tabloda bir liste şeklinde tutuyoruz
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities;
}
