package com.example.bankapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "agreements")
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @JoinColumn(name = "account_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @NonNull
    private Account accountId;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @NonNull
    private Product productId;

    @Column(name = "interest_rate")
    @NonNull
    private Double interestRate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NonNull
    private Status status;

    @Column(name = "limit")
    @NonNull
    private Integer limit;

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
        Agreement agreement = (Agreement) o;

        return id == agreement.id && accountId == agreement.accountId && productId == agreement.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, productId);
    }

    public Instant getStartDate() {
        return null;
    }

    public Instant getEndDate() {
        return null;
    }
}
