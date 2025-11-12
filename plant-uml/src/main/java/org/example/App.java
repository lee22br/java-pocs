package org.example;

import net.sourceforge.plantuml.SourceStringReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App 
{
    public void generateDiagramFromFile(String pumlFilePath, String outputFileName) {
        System.out.println("Reading file: " + pumlFilePath);

        try {

            String pumlSource = new String(Files.readAllBytes(Paths.get(pumlFilePath)));

            SourceStringReader reader = new SourceStringReader(pumlSource);

            File output = new File(outputFileName);

            try (FileOutputStream fos = new FileOutputStream(output)) {
                reader.outputImage(fos).getDescription();

                System.out.println("UML Diagram generated!");
                System.out.println(" File output: " + output.getAbsolutePath());
            }

        } catch (IOException e) {
            System.err.println("Error while reading file");
            throw new RuntimeException(e);
        }
    }

    static void main(String[] args)
    {
        App app = new App();
        IO.println("Write file .puml to generate UML diagram ");
        String file = IO.readln("File: ");
        String output = file + "_uml";
        app.generateDiagramFromFile(file, output);
    }
}
