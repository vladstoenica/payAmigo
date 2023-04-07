package com.example.payAmigo.service;

import com.example.payAmigo.entity.User;
import com.example.payAmigo.entity.Wallet;
import com.example.payAmigo.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public List<Wallet> getWalletsByUser(User user) {
        return walletRepository.findByUser(user);
    }

    public Wallet getWalletByName(String name) {
        return walletRepository.findByName(name);
    }

    public Wallet getWalletByBalance(double balance) {
        return walletRepository.findByBalance(balance);
    }

    public List<Wallet> getWalletsByBalanceGreaterThan(double balance) {
        return walletRepository.findByBalanceGreaterThan(balance);
    }

    public List<Wallet> getWalletsByCurrency(String currency) {
        return walletRepository.findByCurrency(currency);
    }

}
