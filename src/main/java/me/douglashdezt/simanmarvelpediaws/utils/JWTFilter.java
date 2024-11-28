package me.douglashdezt.simanmarvelpediaws.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import me.douglashdezt.simanmarvelpediaws.models.User;
import me.douglashdezt.simanmarvelpediaws.services.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JWTFilter extends OncePerRequestFilter {
    private final JWTTools jwtTools;
    private final UserService userService;

    public JWTFilter(JWTTools jwtTools, UserService userService) {
        this.jwtTools = jwtTools;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenHeader = request.getHeader("Authorization");
        String email = null;
        String token = null;

//        Token verification
        if(tokenHeader != null && tokenHeader.startsWith("Bearer ") && tokenHeader.length() > 7) {
            token = tokenHeader.substring(7);
            if(jwtTools.verifyToken(token)) {
                email = jwtTools.getIdentifierFrom(token);
            }
        }

//        User verification
        if(token != null && email != null &&  SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userService.findUserByEmail(email);
            log.info(user == null ? "User not found" : user.getEmail());

            if(user != null) {
                //Preparing the authentication token.
                UsernamePasswordAuthenticationToken authToken
                        = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                //This line, sets the user to security context to be handled by the filter chain
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
