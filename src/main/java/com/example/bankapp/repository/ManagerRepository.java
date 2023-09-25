package com.example.bankapp.repository;

import com.example.bankapp.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, String> {
    void deleteByName(String name);
}
