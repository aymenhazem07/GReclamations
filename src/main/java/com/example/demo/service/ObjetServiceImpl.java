package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Objet;
import com.example.demo.repository.ObjetRepository;

import java.util.List;

@Service
public class ObjetServiceImpl implements ObjetService {
    
    private final ObjetRepository objetRepository;
    
    public ObjetServiceImpl(ObjetRepository objetRepository) {
        this.objetRepository = objetRepository;
    }
    
    @Override
    public List<Objet> getAllObjets() {
        return objetRepository.findAll();
    }
    
    @Override
    public Objet getObjetById(Long idObjet) {
        return objetRepository.findById(idObjet)
                .orElseThrow(() -> new IllegalArgumentException("Invalid objet ID: " + idObjet));
    }
    
    @Override
    public Objet saveObjet(Objet objet) {
        return objetRepository.save(objet);
    }
    
    @Override
    public void deleteObjet(Long idObjet) {
        objetRepository.deleteById(idObjet);
    }
    
    @Override
    public Page<Objet> findPaginatedObjet(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
            Sort.by(sortField).descending();
        
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return objetRepository.findAll(pageable);
    }
}
