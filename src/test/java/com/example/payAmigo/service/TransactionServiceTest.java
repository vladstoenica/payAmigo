package com.example.payAmigo.service;

import com.example.payAmigo.entity.Transaction;
import com.example.payAmigo.entity.Wallet;
import com.example.payAmigo.repository.TransactionRepository;
import com.example.payAmigo.repository.WalletRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private WalletRepository walletRepository;

    @Test
    public void testProcessTransactionInsufficientBalance() throws Exception {
        // Arrange
        Wallet sourceWallet = new Wallet();
        sourceWallet.setId(1);
        sourceWallet.setBalance(100);

        Wallet destinationWallet = new Wallet();
        destinationWallet.setId(2);
        destinationWallet.setBalance(50);

        Transaction transaction = new Transaction();
        transaction.setSourceWallet(sourceWallet);
        transaction.setDestinationWallet(destinationWallet);
        transaction.setAmount(150);

        when(walletRepository.getBalance(eq(1))).thenReturn(100.0);

        Exception exception = assertThrows(Exception.class, () -> {
            transactionService.processTransaction(transaction);
        });
        assertEquals("Insufficient money in source wallet", exception.getMessage());

        verify(walletRepository).getBalance(1);

    }

    @Test
    public void testDestinationWalletExists() {
        int destinationId = 1;
        transactionService.destinationWalletExists(destinationId);
        verify(transactionRepository).existsDestinationWalletById(destinationId);
    }

    @Test
    public void testSourceWalletExists() {
        int sourceId = 1;
        transactionService.sourceWalletExists(sourceId);
        verify(transactionRepository).existsSourceWalletById(sourceId);
    }

}
