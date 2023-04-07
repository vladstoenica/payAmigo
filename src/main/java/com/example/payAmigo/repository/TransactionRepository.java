package com.example.payAmigo.repository;


import com.example.payAmigo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    boolean existsDestinationWalletById(int destinationId);

    boolean existsSourceWalletById(int sourceId);
}
