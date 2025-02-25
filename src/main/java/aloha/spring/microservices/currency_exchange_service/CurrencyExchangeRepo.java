package aloha.spring.microservices.currency_exchange_service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange, Long>{
    Optional<CurrencyExchange> findByFromAndTo(String from, String to);
}
