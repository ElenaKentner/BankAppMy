package com.example.bankapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @JoinColumn(name = "client_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @NonNull
    private Client clientId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NonNull
    private Status status;

    @Column(name = "currency_code")
    @NonNull
    private Integer currencyCode;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "balance")
    @NonNull
    private Double balance;

    @Column(name = "name")
    @NonNull
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountId")
    private List<Agreement> agreements;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "debitAccountId")
    private List<Transaction> debitTransactions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creditAccountId")
    private List<Transaction> creditTransactions;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && status == account.status && Objects.equals(name, account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, name);
    }
}
