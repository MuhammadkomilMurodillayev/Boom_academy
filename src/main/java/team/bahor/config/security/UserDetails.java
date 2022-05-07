package team.bahor.config.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import team.bahor.entity.user.AuthUser;
import team.bahor.enums.Role;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private String id;
    private String username;
    private String password;
    private short status;
    private boolean deleted;
    private Set<GrantedAuthority> authorities;

    public UserDetails(AuthUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.status = user.getStatus();
        this.deleted = user.isDeleted();
        this.authorities = processAuthorities(user.getRole());
    }

    private Set<GrantedAuthority> processAuthorities(Role role) {
        authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
        return status == 0;
    }
}
