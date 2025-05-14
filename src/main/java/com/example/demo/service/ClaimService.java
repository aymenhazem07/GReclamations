package com.example.demo.service;

import com.example.demo.model.Claim;

import java.util.List;

import org.springframework.data.domain.Page;

public interface ClaimService {
    
    List<Claim> getAllClaims(); 
    
    Claim saveClaim(Claim claim);
    
    Claim getClaimById(long id);
    
    void deleteClaim(long id);


	Page<Claim> findPaginatedClaim(int pageNo, int pageSize, String sortField, String sortDirection);

	Page<Claim> findPaginatedClaimXml(int pageNo, int pageSize, String sortField, String sortDirection);

	
}

