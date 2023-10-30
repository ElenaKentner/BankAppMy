package com.example.bankapp.entity;

import com.example.bankapp.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonIgnore
    @JoinColumn(name = "debit_account_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account debitAccount;

    @JsonIgnore
    @JoinColumn(name = "credit_account_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account creditAccount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", status=" + status +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}