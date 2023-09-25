package com.example.bankapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "manager_id")
    @NonNull
    private Integer managerId;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NonNull
    private Status status;

    @Column(name = "currency_code")
    @NonNull
    private Integer currencyCode;

    @Column(name = "interest_rate")
    @NonNull
    private Double interestRate;

    @Column(name = "limit")
    @NonNull
    private Double limit;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Agreement> agreements;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(managerId, product.managerId) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, managerId, name);
    }
}
