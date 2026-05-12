package Javastral.com.gestorMateriasWeb.web.controller;

import Javastral.com.gestorMateriasWeb.security.jwt.UserDetailsImpl;
import Javastral.com.gestorMateriasWeb.web.controller.response.Response;
import Javastral.com.gestorMateriasWeb.web.controller.response.Meta;
import Javastral.com.gestorMateriasWeb.web.controller.response.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/whoami")
public class WhoAmIController {

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<Response<User>> whoAmI() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UserDetailsImpl details)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response<>(
                    null,
                    Meta.empty(),
                    new Error("User authentication failed",HttpStatus.FORBIDDEN.toString())
                    ));
        }
        return ResponseEntity.ok(new Response<>(
                new User(details.getId(), details.getUsername(), details.getEmail(), details.getRoles()),
                Meta.empty(),
                null));
    }

}

record User(long id, String username, String email, Set<String> roles) {}