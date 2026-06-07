package com.deepak.gitinsight.Security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.deepak.gitinsight.Service.RateLimiterService;

@Component
public class RateLimiterFilter extends OncePerRequestFilter {
    private RateLimiterService RateLimiterService;
     public RateLimiterFilter(RateLimiterService RateLimiterService) {
        this.RateLimiterService = RateLimiterService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       String path=request.getRequestURI();
       if(path.startsWith("/api/github/") || path.startsWith("/api/tracked-users")) {
       String ip=request.getRemoteAddr();
       if(!RateLimiterService.isRequestAllowed(ip)) {
       response.setStatus(429);
       response.setContentType("application/json");
       response.getWriter().write("{\"message\":\"Too many requests. Please try again later.\"}");
       return;
       }
    }
    filterChain.doFilter(request, response);
    }

}
