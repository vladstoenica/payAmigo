package com.example.payAmigo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    private int id;

    @Column
    private String name;

    @Column
    private double balance;

    @Column
    private String currency;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
