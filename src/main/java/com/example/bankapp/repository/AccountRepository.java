package com.example.bankapp.repository;

import com.example.bankapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query("select a from Account a where a.agreement.product.name = :name")
    List<Account> getByProductName(@Param("name") String productName);

    @Query("select a from Account a where a.name = :accountName")
    Optional<Account> findByName(@Param("accountName") String accountName);

}

