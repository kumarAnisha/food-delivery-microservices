package com.foorDelivery.apiGateway;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
@Component
public class AuthValidationFilter extends AbstractGatewayFilterFactory<AuthValidationFilter.Config>  {

        @Autowired
        private JwtUtil jwtUtil;

        public AuthValidationFilter() {
            super(Config.class);
        }

        @Override
        public GatewayFilter apply(Config config) {

            return (exchange, chain) -> {

                String path = exchange.getRequest().getURI().getPath();

                // Whitelist auth endpoints
                if (path.contains("/auth/login") || path.contains("/auth/signup") || path.contains("/eureka")) {
                    return chain.filter(exchange);
                }
                String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

                if (authHeader == null || !authHeader.trim().toLowerCase().startsWith("bearer ")) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }

                String token = authHeader.trim().substring(7).trim();
                if ((token.startsWith("\"") && token.endsWith("\"")) || (token.startsWith("'") && token.endsWith("'"))) {
                    token = token.substring(1, token.length() - 1);
                }

                try {
                    jwtUtil.validateToken(token);
                } catch (Exception e) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }

                return chain.filter(exchange);
            };
        }

        public static class Config {
        }
    }
