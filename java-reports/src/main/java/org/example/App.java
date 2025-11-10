package org.example;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;


import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.Date;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        VelocityEngine ve = new VelocityEngine();

        Properties p = new Properties();
        p.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        p.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init(p);

        VelocityContext context = new VelocityContext();


        context.put("name", "Leandro Iglezias");
        context.put("numMsg", 42);
        context.put("date", new Date());

        Template template = ve.getTemplate("velocity.vm", "UTF-8");

        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        String htmlContent = writer.toString();
        System.out.println(writer.toString());

        final String DEST = "relatorio.pdf";
        try {
            ConverterProperties properties = new ConverterProperties();
            properties.setBaseUri("./src/main/resources/html/");
            PdfWriter writerPdf = new PdfWriter(DEST, new WriterProperties().setFullCompressionMode(true));
            FileOutputStream os = new FileOutputStream("relatorio.pdf");

            HtmlConverter.convertToPdf(htmlContent, writerPdf, properties);
            os.close();
            System.out.println("PDF gerado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
