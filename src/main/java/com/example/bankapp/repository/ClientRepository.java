package com.example.bankapp.repository;

import com.example.bankapp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    @Procedure(value = "GetClientById")
    Optional<Client> findClientById(@Param(value = "input_client_id") byte[] id);

    @Query("select c from Client c where c.email = :email")
    Optional<Client> findByEmail(@Param("email") String email);
}
