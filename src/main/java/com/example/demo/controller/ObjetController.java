package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.example.demo.model.Objet;
import com.example.demo.service.ObjetService;

import java.util.List;

@Controller
public class ObjetController {

    private final ObjetService objetService;

    @Autowired
    public ObjetController(ObjetService objetService) {
        this.objetService = objetService;
    }

    @GetMapping("/showListeObjet")
    public String viewListePageObjet(Model model) {
        List<Objet> listObjets = objetService.getAllObjets();
        model.addAttribute("listObjets", listObjets);
        return "liste-objet";
    }

    @GetMapping("/objets/{idObjet}")
    public String viewObjetDetails(@PathVariable("idObjet") Long idObjet, Model model) {
        Objet objet = objetService.getObjetById(idObjet);
        model.addAttribute("objet", objet);
        return "objet-details";
    }

    @GetMapping("/objets-new")
    public String showObjetForm(Model model) {
        model.addAttribute("objet", new Objet());
        return "new-Objet";
    }

    @PostMapping("/saveobjets")
    public String saveObjet(@ModelAttribute("objet") Objet objet) {
        objetService.saveObjet(objet);
        return "redirect:/showListeObjet";
    }

    @GetMapping("/updateObjet/{idObjet}")
    public String showEditObjetForm(@PathVariable("idObjet") Long idObjet, Model model) {
        Objet objet = objetService.getObjetById(idObjet);
        model.addAttribute("objet", objet);
        return "update-objet";
    }

    @GetMapping("/deleteObjet/{idObjet}")
    public String deleteObjet(@PathVariable("idObjet") Long idObjet) {
        objetService.deleteObjet(idObjet);
        return "redirect:/showListeObjet";
    }
}
