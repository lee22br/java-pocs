package org.example;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

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

        Template template = ve.getTemplate("velocity.vm");

        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        System.out.println(writer.toString());
    }
}
