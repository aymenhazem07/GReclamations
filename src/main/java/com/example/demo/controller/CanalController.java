package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Canal;
import com.example.demo.service.CanalService;

import java.util.List;

@Controller
public class CanalController {

	@Autowired
	private CanalService canalService;

 

    @GetMapping("/showListeCanal")
    public String viewListePageCanal(Model model) {
        List<Canal> listCanals = canalService.getAllCanals();
        model.addAttribute("listCanals", listCanals);
        return "liste-canal";
    }

    @GetMapping("/canal-new")
    public String showNewCanalForm(Model model) {
    	// create model attribute to bind form data
    	Canal canal = new Canal();
    	model.addAttribute("canal", canal);
        return "new-canal";
    }

    @PostMapping("/savecanals")
    public String saveCanal(@ModelAttribute("canal") Canal canal) {
    	// save canal to database
    	canalService.saveCanal(canal);
        return "redirect:/showListeCanal";
    }

    @GetMapping("/updateCanal/{idCanal}")
    public String showFormForUpdate(@PathVariable ( value = "idCanal") long idCanal, Model model) {

    	// get canal from the service
    	Canal canal = canalService.getCanalById(idCanal);

    	// set canal as a model attribute to pre-populate the form
    	model.addAttribute("canal", canal);
        return "update-canal"; 
    }
    
    @GetMapping("/deleteCanal/{idCanal}")
    public String deleteCanal(@PathVariable (value = "idCanal") long idCanal) {

    	// call delete canal method 
    	this.canalService.deleteCanalById(idCanal);
        return "redirect:/showListeCanal";
    }
}
