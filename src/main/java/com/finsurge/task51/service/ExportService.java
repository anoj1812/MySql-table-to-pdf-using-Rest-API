package com.finsurge.task51.service;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import com.finsurge.task51.entity.Student;
import jakarta.servlet.http.HttpServletResponse;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Service;

@Service
public class ExportService {
    private List<Student> listStudents;

    public ExportService(List<Student> listStudents) {
        this.listStudents = listStudents;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Student Id", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Student Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Student Dept", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Student Clg", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (Student student : listStudents) {
            table.addCell(String.valueOf(student.getStudentId()));
            table.addCell(student.getStudentName());
            table.addCell(student.getStudentDept());
            table.addCell(student.getStudentClg());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Students", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
