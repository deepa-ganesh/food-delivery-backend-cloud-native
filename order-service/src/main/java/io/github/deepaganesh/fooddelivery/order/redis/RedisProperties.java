package io.github.deepaganesh.fooddelivery.order.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {
    private String host = "localhost";
    private int port = 6379;
    private long timeout = 60000;

    private Lettuce lettuce = new Lettuce();

    @Getter
    @Setter
    public static class Lettuce {
        private Pool pool = new Pool();

        @Getter
        @Setter
        public static class Pool {
            private int maxActive = 10;
            private int maxIdle = 10;
            private int minIdle = 2;
            private long maxWait = -1L;
        }
    }

    private Sentinel sentinel;

    @Getter
    @Setter
    public static class Sentinel {
        private String master = "mymaster";
        private List<String> nodes = List.of("localhost:26379", "localhost:26380", "localhost:26381");
        private String password = "";
    }
}
