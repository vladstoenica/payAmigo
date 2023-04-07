package com.example.payAmigo.service;

import com.example.payAmigo.entity.Transaction;
import com.example.payAmigo.entity.User;
import com.example.payAmigo.entity.Wallet;
import com.example.payAmigo.repository.TransactionRepository;
import com.example.payAmigo.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletRepository walletRepository;

    public void processTransaction(Transaction transaction) throws Exception {
        Wallet sourceWallet = transaction.getSourceWallet();
        double amount = transaction.getAmount();
        double balance = walletRepository.getBalance(sourceWallet.getId());
        if (balance < amount) {
            throw new Exception("Insufficient money in source wallet");
        }
        // updatam suma in sursa
        sourceWallet.setBalance(balance - amount);
        walletRepository.save(sourceWallet);
        // updatam suma in destinatie
        Wallet destinationWallet = transaction.getDestinationWallet();
        double destinationBalance = walletRepository.getBalance(destinationWallet.getId());
        destinationWallet.setBalance(destinationBalance + amount);
        walletRepository.save(destinationWallet);
        // save transaction
        transaction.setCreatedAt(new Date());
        transactionRepository.save(transaction);
    }

    public boolean destinationWalletExists(int destinationId) {
        return transactionRepository.existsDestinationWalletById(destinationId);
    }

    public boolean sourceWalletExists(int destinationId) {
        return transactionRepository.existsSourceWalletById(destinationId);
    }
}


