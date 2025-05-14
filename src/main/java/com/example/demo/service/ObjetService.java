package com.example.demo.service;

import com.example.demo.model.Objet;

import java.util.List;

import org.springframework.data.domain.Page;

public interface ObjetService {
    
    List<Objet> getAllObjets();
    
    Objet getObjetById(Long idObjet);
    
    Objet saveObjet(Objet objet);
    
    void deleteObjet(Long idObjet);

    Page<Objet> findPaginatedObjet(int pageNo, int pageSize, String sortField, String sortDirection);
}
