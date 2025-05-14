package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
}

