package com.undina.gateway.service;

import com.undina.gateway.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SecurityService {
    public boolean getAuth() {
        log.info("getAuth - start");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof SecurityUser) {
                SecurityUser user = (SecurityUser) principal;
                if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                    log.info("getAuth - end");
                    return true;
                }
            }
        }
        log.info("getAuth - SecurityException");
        throw new SecurityException("you not admin");
    }
}
