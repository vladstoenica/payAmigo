package com.example.payAmigo.service;

import com.example.payAmigo.entity.User;
import com.example.payAmigo.entity.Wallet;
import com.example.payAmigo.repository.WalletRepository;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    @InjectMocks
    private WalletService walletService;

    @Test
    public void testWalletsFindByUser() {
        User user = new User();
        List<Wallet> wallets = new ArrayList<>();
        Wallet wallet1 = new Wallet();
        wallets.add(wallet1);
        Wallet wallet2 = new Wallet();
        wallets.add(wallet2);
        // mock the service ca sa returneze o lista de wallets pt un user
        when(walletService.getWalletsByUser(user)).thenReturn(wallets);

        List<Wallet> result = walletService.getWalletsByUser(user);
        verify(walletRepository).findByUser(user);

        //verificam daca lista chiar are portofelele
        assertEquals(2, result.size());
        assertTrue(result.contains(wallet1));
        assertTrue(result.contains(wallet2));
    }

    @Test
    public void testGetWalletByName() {
        String walletName = "testWallet";
        Wallet testWallet = new Wallet();
        testWallet.setName(walletName);

        when(walletRepository.findByName(walletName)).thenReturn(testWallet);

        Wallet result = walletService.getWalletByName(walletName);

        assertEquals(testWallet, result);
    }

    @Test
    public void testFindByBalance() {
        double balance = 100.0;
        Wallet wallet = new Wallet();
        wallet.setBalance(balance);
        when(walletRepository.findByBalance(balance)).thenReturn(wallet);
        Wallet result = walletService.getWalletByBalance(balance);
        assertEquals(wallet, result);
    }

    @Test
    public void testFindByBalanceGreaterThan() {
        List<Wallet> wallets = new ArrayList<>();
        Wallet wallet1 = new Wallet();
        wallet1.setBalance(100.0);
        wallets.add(wallet1);
        Wallet wallet2 = new Wallet();
        wallet2.setBalance(200.0);
        wallets.add(wallet2);

        when(walletRepository.findByBalanceGreaterThan(150.0)).thenReturn(wallets);
        List<Wallet> result = walletService.getWalletsByBalanceGreaterThan(150.0);
        verify(walletRepository).findByBalanceGreaterThan(150.0);
        assertEquals(2, result.size());
        assertTrue(result.contains(wallet2));
    }

    @Test
    public void testFindByCurrency() {
        Wallet wallet1 = new Wallet();
        wallet1.setCurrency("RON");
        Wallet wallet2 = new Wallet();
        wallet2.setCurrency("EUR");

        List<Wallet> wallets = new ArrayList<>();
        wallets.add(wallet1);
        wallets.add(wallet2);

        when(walletRepository.findByCurrency("RON")).thenReturn(Collections.singletonList(wallet1));
        walletService.getWalletsByCurrency("RON");
        Mockito.verify(walletRepository).findByCurrency("RON");

        assertTimeout(Duration.ofSeconds(1), () -> {
            List<Wallet> result = walletService.getWalletsByCurrency("RON");
            assertEquals(1, result.size());
            assertTrue(result.contains(wallet1));
            assertFalse(result.contains(wallet2));
        });
    }

}
