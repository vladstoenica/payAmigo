package com.example.payAmigo.controller;

import com.example.payAmigo.entity.Wallet;
import com.example.payAmigo.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    //facut pt teste
}
