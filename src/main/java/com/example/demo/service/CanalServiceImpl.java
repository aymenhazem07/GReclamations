package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Canal;
import com.example.demo.repository.CanalRepository;



import java.util.List;
import java.util.Optional;

@Service
public class CanalServiceImpl implements CanalService {

	@Autowired
	private CanalRepository canalRepository;

	@Override
	public List<Canal> getAllCanals() {
		return canalRepository.findAll();
	}

	@Override
	public void saveCanal(Canal canal) {
		this.canalRepository.save(canal);
	}

	@Override
	public Canal getCanalById(long idCanal) {
		Optional<Canal> optional = canalRepository.findById(idCanal);
		Canal canal = null;
		if (optional.isPresent()) {
			canal = optional.get();
		} else {
			throw new RuntimeException("Canal not found for id :: " + idCanal);
		}
		return canal;
	}

	@Override
	public void deleteCanalById(long idCanal) {
		this.canalRepository.deleteById(idCanal);
	}

	

	

	@Override
	public Page<Canal> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		// TODO Auto-generated method stub
		return null;
	}

}

