package aloha.spring.microservices.currency_exchange_service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Currency exchange not found.")
public class CurrencyExchangeNotFoundException extends RuntimeException {
}
