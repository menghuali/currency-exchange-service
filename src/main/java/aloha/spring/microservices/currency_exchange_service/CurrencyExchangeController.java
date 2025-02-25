package aloha.spring.microservices.currency_exchange_service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment env;
    
    @Autowired
    private CurrencyExchangeRepo repo;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getExchangeValue(@PathVariable String from, @PathVariable String to) {
        Optional<CurrencyExchange> result = repo.findByFromAndTo(from, to);
        if (result.isPresent()) {
            CurrencyExchange exchange = result.get();
            exchange.setEnvironment(env.getProperty("local.server.port"));
            return exchange;
        } else {
            throw new CurrencyExchangeNotFoundException();
        }
    }

}
