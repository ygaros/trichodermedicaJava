package jureczko.page.security.filters;

import jureczko.page.exceptions.AccessTokenExpired;
import jureczko.page.security.JwtProvider;
import jureczko.page.security.SecurityCipher;
import jureczko.page.security.UserPrincipleUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private static final String BEARER = "Bearer";

    private final UserPrincipleUserDetailsService userDetailsService;
    @Value("${security.jwt.token.accessTokenCookieName}")
    private String accessTokenCookieName;
    @Value("${security.jwt.token.refreshTokenCookieName}")
    private String refreshTokenCookieName;
    private final JwtProvider jwtProvider;

    public JwtFilter(UserPrincipleUserDetailsService userDerailsSerivce,
                     JwtProvider jwtProvider){
        this.userDetailsService = userDerailsSerivce;
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException, AccessTokenExpired {

        if(request.getRequestURI().contains("admin")) {
            String[] jwt = getJwtToken(request, true);
            if (StringUtils.hasText(jwt[0]) && jwtProvider.isValidToken(jwt[0])) {
                processAuth(request, this.getPrincipalFromToken(jwt[0]));
            } else if (StringUtils.hasText(jwt[1]) && jwtProvider.isValidToken(jwt[1])) {
                processAuth(request, this.getPrincipalFromToken(jwt[1]));
                FilterConfig filterConfig = this.getFilterConfig();
                if (null != filterConfig) {
                    response.addHeader("alicja", request.getRequestURI());
                    filterConfig.getServletContext().getRequestDispatcher("/api/refresh").forward(request, response);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private void processAuth(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    private UserDetails getPrincipalFromToken(String token){
        String username = jwtProvider.getUsername(token);
        return userDetailsService.loadUserByUsername(username);
    }


    private String[] getJwtToken(HttpServletRequest request, boolean fromCookie) {
        if (fromCookie) return getJwtFromCookie(request);
        return null;
        //return getJwtFromRequest(request);
    }
//    private String[] getJwtFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            String accessToken = bearerToken.substring(7);
//            if (accessToken == null) return getJwtFromCookie(request);
//
//            return SecurityCipher.decrypt(accessToken);
//        }
//        return null;
//    }

    private String[] getJwtFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String[] result = new String[2];
        result[0] = "";
        result[1] = "";
        if (null == cookies) return result;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(accessTokenCookieName)) {
                result[0] = SecurityCipher.decrypt(cookie.getValue());
            } else if (cookie.getName().equals(refreshTokenCookieName)) {
                result[1] = SecurityCipher.decrypt(cookie.getValue());
            }
        }
        return result;
    }

    private Optional<String> getBearerToken(String headerVal) {
        if (headerVal != null && headerVal.startsWith(BEARER)) {
            return Optional.of(headerVal.replace(BEARER, "").trim());
        }
        return Optional.empty();
    }
}

