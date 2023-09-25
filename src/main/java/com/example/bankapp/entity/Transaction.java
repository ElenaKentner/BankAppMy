package com.example.bankapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @JoinColumn(name = "debit_account_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @NonNull
    private Account debitAccountId;

    @JoinColumn(name = "credit_account_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @NonNull
    private Account creditAccountId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NonNull
    private Status status;

    @Column(name = "amount")
    @NonNull
    private Double amount;

    @Column(name = "description")
    @NonNull
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id && debitAccountId == that.debitAccountId && creditAccountId == that.creditAccountId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, debitAccountId, creditAccountId);
    }
}
