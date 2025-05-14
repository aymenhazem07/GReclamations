package com.example.demo.service;

import com.example.demo.model.Canal;



import java.util.List;

import org.springframework.data.domain.Page;

public interface CanalService {
    
	
		List<Canal> getAllCanals();
		void saveCanal(Canal canal);
		Canal getCanalById(long idCanal);
		void deleteCanalById(long idCanal);
		Page<Canal> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
		

   
}
