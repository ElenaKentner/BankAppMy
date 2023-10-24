package com.example.bankapp.entity;

import com.example.bankapp.entity.enums.CurrencyCode;
import com.example.bankapp.entity.enums.Status;
import com.example.bankapp.entity.enums.Type;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "currency_code")
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = ALL, mappedBy = "account")
    private Agreement agreement;

    @JsonIgnore
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @JsonIgnore
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = ALL,
            mappedBy = "debitAccount"
    )
    private List<Transaction> debitTransactions;

    @JsonIgnore
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = ALL,
            mappedBy = "creditAccount"
    )
    private List<Transaction> creditTransactions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(createdAt, account.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", status=" + status +
                ", currencyCode=" + currencyCode +
                ", type=" + type +
                ", balance=" + balance +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
