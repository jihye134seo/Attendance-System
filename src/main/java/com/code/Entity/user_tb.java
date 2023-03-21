//https://velog.io/@rainbowweb/%EC%8A%A4%ED%94%84%EB%A7%81%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0-3-JWT-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EB%B0%A9%EC%8B%9D
//https://gksdudrb922.tistory.com/217

package com.code.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class user_tb implements UserDetails {

    @Id
    @Column(updatable = false, unique = true, nullable = false)
    private Integer uid;

    @Column(nullable = false)
    private String email_address;

    @Column(nullable = false)
    private String password;


    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    @Override
    public String getUsername() {
        return email_address;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
