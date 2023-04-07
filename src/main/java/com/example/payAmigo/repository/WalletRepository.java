package com.example.payAmigo.repository;

import com.example.payAmigo.entity.User;
import com.example.payAmigo.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    List<Wallet> findByUser(User user);
    Wallet findByName(String name);
    Wallet findByBalance(double amount);
    List<Wallet> findByBalanceGreaterThan(double amount);
    List<Wallet> findByCurrency(String currency);

    @Query("SELECT w.balance FROM Wallet w WHERE w.id = :walletId")
    double getBalance(@Param("walletId") int walletId);

}

