package io.github.deepaganesh.fooddelivery.order.config;

import io.github.deepaganesh.fooddelivery.order.redis.RedisRateLimiterService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    @Autowired
    private RedisRateLimiterService rateLimiterService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String clientId = request.getRemoteAddr(); // or JWT subject, API key, etc.

        if (!rateLimiterService.isAllowed(clientId)) {
            response.setStatus(HttpStatus.SC_TOO_MANY_REQUESTS);
            response.getWriter().write("Rate limit exceeded. Please try again later.");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
