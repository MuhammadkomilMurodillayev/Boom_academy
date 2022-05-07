package team.bahor.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import team.bahor.entity.user.Principal;
import team.bahor.repositories.auth.AuthUserRepository;

public class Utils {

    public static String getSessionId() {
        return ((Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId().replace("/","");
    }

    public static boolean sessionHasRole(String role) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().
                filter(grantedAuthority -> grantedAuthority.getAuthority().equalsIgnoreCase("ROLE_" + role))
                .count() == 1;
    }

    public static boolean sessionHasAnyRole(String... roles) {
        for (String role : roles) {
            if (sessionHasRole(role)) {

                return true;

            }
        }
        return false;
    }
}
