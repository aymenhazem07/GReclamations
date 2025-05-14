package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.excelExport.ClaimExport;
import com.example.demo.model.Canal;
import com.example.demo.model.Claim;
import com.example.demo.model.Objet;
import com.example.demo.service.CanalService;
import com.example.demo.service.ClaimService;
import com.example.demo.service.ObjetService;
import com.example.demo.xmlExport.ClaimXmlExport;



import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ClaimController {
	
	@Autowired
    private final ClaimService claimService;
    @Autowired
    private CanalService canalService;
    @Autowired
    private ObjetService objetService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping("/showListeClaim")
    public String viewListeClaim(Model model) {
        List<Claim> listClaims = claimService.getAllClaims();
        model.addAttribute("listClaims", listClaims);
        return "liste-claims";
    }
    

    @GetMapping("/showListeClaimdetails")
    public String viewListeClaimdetails(Model model) {
        List<Claim> listClaims = claimService.getAllClaims();
        model.addAttribute("listClaims", listClaims);
        return "liste-details";
    }

    @GetMapping("/showListeClaimxml")
    public String viewListeClaimXml(Model model) {
        List<Claim> listClaims = claimService.getAllClaims();
        model.addAttribute("listClaims", listClaims);
        return "liste-claims-xml";
    }

    @GetMapping("/claims/{id}")
    public String viewClaimDetails(@PathVariable("id") long id, Model model) {
        Claim claim = claimService.getClaimById(id);
        model.addAttribute("claim", claim);
        return "claim-details";
    }

    @GetMapping("/claims-new")
    public String showClaimForm(Model model) {
        model.addAttribute("claim", new Claim());
        List<Canal> listCanals = canalService.getAllCanals();
        List<Objet> listObjets = objetService.getAllObjets();
        model.addAttribute("listCanals", listCanals);
        model.addAttribute("listObjets", listObjets);
        return "new-claims";
    }

    @PostMapping("/saveclaims")
    public String saveClaim(@ModelAttribute("claim") Claim claim,
                            @RequestParam("idCanal") Long idCanal,
                            @RequestParam("idObjet") Long idObjet) {
        Canal canal = canalService.getCanalById(idCanal);
        Objet objet = objetService.getObjetById(idObjet);
        claim.setCanal(canal);
        claim.setObjet(objet);
        claimService.saveClaim(claim);
        return "redirect:/showListeClaim";
    }

    @GetMapping("/updateClaim/{id}")
    public String showEditClaimForm(@PathVariable(value = "id") long id, Model model) {
        Claim claim = claimService.getClaimById(id);
        model.addAttribute("claim", claim);
        return "update-Claim";
        
    }

    @GetMapping("/deleteClaim/{id}")
    public String deleteClaim(@PathVariable("id") long id) {
        claimService.deleteClaim(id);
        return "redirect:/showListeClaim";
    }

    @GetMapping("/statusclaims/{id}")
    public String changeClaimStatus(@PathVariable("id") Long id, Model model) {
        return "redirect:/claims/" + id;
    }

    @GetMapping("/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=claims_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Claim> listClaims = claimService.getAllClaims();

        ClaimExport excelExporter = new ClaimExport(listClaims);

        excelExporter.export(response);
    }

    @GetMapping("/pdf")
    public void exportToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=claims_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Claim> listClaims = claimService.getAllClaims();

        ClaimExport pdfExporter = new ClaimExport(listClaims);

        pdfExporter.export(response);
    }

    @GetMapping("/xml")
    public void exportToXml(HttpServletResponse response) throws IOException {
        response.setContentType("application/xml");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=claims_" + currentDateTime + ".xml";
        response.setHeader(headerKey, headerValue);

        List<Claim> listClaims = claimService.getAllClaims();

        ClaimXmlExport xmlExporter = new ClaimXmlExport(listClaims);

        xmlExporter.export(response);
    }
}
