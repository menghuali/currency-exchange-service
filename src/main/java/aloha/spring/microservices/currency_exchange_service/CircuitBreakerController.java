package aloha.spring.microservices.currency_exchange_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CircuitBreakerController {

    @GetMapping(path = "/retry", produces = { MediaType.TEXT_PLAIN })
    @Retry(name = "demo", fallbackMethod = "hardcodedResponse")
    public String retry() {
        log.info("Retry call received.");
        String result = new RestTemplate().getForEntity("http://locahost:8080/dummy-url", String.class).getBody();
        return result;
    }

    @GetMapping(path = "/circuit-breaker", produces = { MediaType.TEXT_PLAIN })
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    public String circuiBreaker() {
        log.info("Circuit Breaker call received.");
        String result = new RestTemplate().getForEntity("http://locahost:8080/dummy-url", String.class).getBody();
        return result;
    }

    @GetMapping(path = "/rate-limiter", produces = { MediaType.TEXT_PLAIN })
    @RateLimiter(name = "default")
    public String rateLimit() {
        return "Rate Limiter";
    }

    @GetMapping(path = "/bulkhead", produces = { MediaType.TEXT_PLAIN })
    @Bulkhead(name = "demo")
    public String bulkhead() throws InterruptedException {
        Thread.sleep(1000);
        return "Bulkhead";
    }

    public String hardcodedResponse(Throwable e) {
        return "Hard coded response";
    }

}
