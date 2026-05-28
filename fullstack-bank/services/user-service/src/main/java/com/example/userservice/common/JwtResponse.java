package com.example.userservice.common;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtResponse extends HttpServletResponseWrapper {
        private Long id;
        private JWTPayloadWrapper payloadWrapper;
}
