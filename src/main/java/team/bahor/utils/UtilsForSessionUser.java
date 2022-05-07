package team.bahor.utils;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import team.bahor.entity.user.AuthUser;
import team.bahor.entity.user.Principal;
import team.bahor.repositories.auth.AuthUserRepository;

@Component
public class UtilsForSessionUser {
    public final AuthUserRepository authUserRepository;

    public UtilsForSessionUser(@Lazy AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    public String getSessionId() {
        return authUserRepository.findByUsernameAndDeletedFalse(((Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()).get().getId();
    }

    public boolean hasRole(String role){
        return authUserRepository.findByUsernameAndDeletedFalse(((Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()).get().getRole().name().equalsIgnoreCase(role);
    }
}
