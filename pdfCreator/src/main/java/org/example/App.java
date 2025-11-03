package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.IOException;


/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        PDFont pdfFont=  new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
        contentStream.setFont(pdfFont, 24);
        contentStream.newLineAtOffset(150, 600);
        contentStream.showText("Hello, PDFBox PDF!");
        contentStream.endText();
        contentStream.close();

        document.save("simple_pdfbox.pdf");
        document.close();
        System.out.println("PDF created successfully with PDFBox!");
    }
}
