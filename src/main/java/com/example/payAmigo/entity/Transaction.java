package com.example.payAmigo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    private int id;

    @Column
    private double amount;

    @Column
    private double commissionPercent;

    @Column
    private double commissionAmount;

    @Column
    private String currency;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "source_id")
    private Wallet sourceWallet;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Wallet destinationWallet;

}
