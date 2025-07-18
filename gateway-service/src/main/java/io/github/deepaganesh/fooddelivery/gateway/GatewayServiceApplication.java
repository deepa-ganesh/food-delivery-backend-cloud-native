package io.github.deepaganesh.fooddelivery.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

import java.util.Objects;

@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(
                    Objects.requireNonNull(exchange.getRequest()
                            .getRemoteAddress()).getAddress().getHostAddress());
    }
}
