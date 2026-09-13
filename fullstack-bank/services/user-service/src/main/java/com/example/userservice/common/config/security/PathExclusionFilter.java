package com.example.userservice.common.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.filter.OncePerRequestFilter;

public abstract class PathExclusionFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException{

        var uri = request.getRequestURI();

        if(uri.contains("/swagger-ui") || uri.contains("/v3/api-docs") || uri.contains("/test")){
            /*
                TODO:
                    Inherit whitelist from application.yml
             */
            return true;
        }
        return false;
    }
}
