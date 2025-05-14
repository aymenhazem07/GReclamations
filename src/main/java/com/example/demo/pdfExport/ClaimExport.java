package com.example.demo.pdfExport;

import com.example.demo.model.Canal;
import com.example.demo.model.Claim;
import com.example.demo.model.Objet;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClaimExport {
    private List<Claim> listClaims;

    public ClaimExport(List<Claim> listClaims) {
        this.listClaims = listClaims;
    }

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document();
        try {
            response.setContentType("application/pdf");
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            Font headerFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Font cellFont = new Font(Font.FontFamily.HELVETICA, 14);

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);

            addTableHeader(table, headerFont);
            addRows(table, cellFont);

            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    private void addTableHeader(PdfPTable table, Font font) {
        table.addCell(createCell("Claim ID", font));
        table.addCell(createCell("Name", font));
        table.addCell(createCell("E-mail", font));
        table.addCell(createCell("Objet", font));
        table.addCell(createCell("Description", font));
        table.addCell(createCell("Canal", font));
    }

    private void addRows(PdfPTable table, Font font) {
        for (Claim claim : listClaims) {
            table.addCell(createCell(String.valueOf(claim.getId()), font));
            table.addCell(createCell(claim.getName(), font));
            table.addCell(createCell(claim.getEmail(), font));
            table.addCell(createCell(claim.getObjet(), font));
            table.addCell(createCell(claim.getDescription(), font));
            table.addCell(createCell(claim.getCanal(), font));
        }
    }

    private PdfPCell createCell(Canal canal, Font font) {
		// TODO Auto-generated method stub
		return null;
	}

	private PdfPCell createCell(Objet objet, Font font) {
		// TODO Auto-generated method stub
		return null;
	}

	private PdfPCell createCell(String string, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(string, font));
        cell.setPadding(5);
        return cell;
    }
}
