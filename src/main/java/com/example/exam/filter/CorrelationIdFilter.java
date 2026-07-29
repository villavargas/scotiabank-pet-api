package com.example.exam.filter;

import com.example.exam.constant.PetConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class CorrelationIdFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String correlationId = request.getHeader(PetConstants.CORRELATION_ID_HEADER);
        if (correlationId == null || correlationId.isBlank()) {
            correlationId = UUID.randomUUID().toString();
        }
        MDC.put(PetConstants.MDC_CORRELATION_ID, correlationId);
        response.setHeader(PetConstants.CORRELATION_ID_HEADER, correlationId);
        log.debug("Request [{} {}] correlationId: {}", request.getMethod(), request.getRequestURI(), correlationId);
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(PetConstants.MDC_CORRELATION_ID);
        }
    }
}
