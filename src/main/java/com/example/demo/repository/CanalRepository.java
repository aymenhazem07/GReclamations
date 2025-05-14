package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Canal;

@Repository
public interface CanalRepository extends JpaRepository<Canal, Long> {
}
