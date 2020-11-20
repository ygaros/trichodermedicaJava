package jureczko.page.security.filters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class CustomHeaderFilter extends OncePerRequestFilter {

    private final String headerName;

    public CustomHeaderFilter(@Value("${custom.header.name}")String headerName) {
        this.headerName = headerName;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String headerValue = request.getHeader(headerName);
        if(!request.getRequestURI().contains("/api")){
            filterChain.doFilter(request, response);
            return;
        }
        if(null==headerValue){
            throw new SecurityException("Co to za kombinowanie? =]");
        }
        filterChain.doFilter(request, response);



    }
}
