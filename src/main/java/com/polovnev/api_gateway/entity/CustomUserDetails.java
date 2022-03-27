package com.polovnev.api_gateway.entity;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Data
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private String role;
    private Boolean isConfirmed;

    public CustomUserDetails(UserEntity user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole().getRole();
        this.isConfirmed = user.getIsConfirmed();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return isConfirmed;
    }
}
