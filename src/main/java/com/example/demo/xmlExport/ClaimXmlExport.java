package com.example.demo.xmlExport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.example.demo.model.Claim;

public class ClaimXmlExport {
    private ClaimListWrapper claimListWrapper;

    public ClaimXmlExport(List<Claim> listClaims) {
        this.claimListWrapper = new ClaimListWrapper(new ArrayList<>(listClaims));
    }






    public void export(HttpServletResponse response) throws IOException {
        response.setContentType("application/xml");
        response.setHeader("Content-Disposition", "attachment; filename=claims.xml");

        try (ServletOutputStream outputStream = response.getOutputStream()) {
            JAXBContext jaxbContext = JAXBContext.newInstance(List.class, Claim.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(claimListWrapper, outputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
           
        }
    }
}
