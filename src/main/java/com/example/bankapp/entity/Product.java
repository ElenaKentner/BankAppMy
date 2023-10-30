package com.example.bankapp.entity;

import com.example.bankapp.entity.enums.CurrencyCode;
import com.example.bankapp.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "currency_code")
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name = "min_limit")
    private Long minLimit;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = ALL, mappedBy = "product")
    private List<Agreement> agreements;

    @JsonIgnore
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Manager manager;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(createdAt, product.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", manager=" + manager +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", currencyCode=" + currencyCode +
                ", interestRate=" + interestRate +
                ", minLimit=" + minLimit +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
