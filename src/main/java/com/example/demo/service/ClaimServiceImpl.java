package com.example.demo.service;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
//import java.io.File;

//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.Marshaller;

import com.example.demo.model.Claim;
import com.example.demo.repository.ClaimRepository;

import java.util.List;
import java.util.Optional;



@Service
public class ClaimServiceImpl implements ClaimService {
    
    private final ClaimRepository claimRepository;
    
    public ClaimServiceImpl(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }
    
    @Override
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }
    
    @Override
    public Claim getClaimById(long id) {
    	Optional<Claim> optional = claimRepository.findById(id);
		Claim claim = null;
		if (optional.isPresent()) {
			claim = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return claim;
	}
    
    @Override
    public Claim saveClaim(Claim claim) {
        return claimRepository.save(claim);
    }
    
    @Override
    public void deleteClaim(long id) {
        claimRepository.deleteById(id);
    }
    
    
	@Override
	public Page<Claim> findPaginatedClaim(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.claimRepository.findAll(pageable);
	}
	
	@Override
	  public Page<Claim> findPaginatedClaimXml(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.claimRepository.findAll(pageable);
	    }

   
 

	
    
}
