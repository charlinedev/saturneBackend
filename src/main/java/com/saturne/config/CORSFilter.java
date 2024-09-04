package com.saturne.config;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "http://127.0.0.1:4200");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
    }

}
