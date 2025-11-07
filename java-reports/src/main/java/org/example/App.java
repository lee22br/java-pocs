package org.example;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Apache Velocity" );
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();

        Template t = velocityEngine.getTemplate("index.vm");

        VelocityContext context = new VelocityContext();
        context.put("name", "World");

        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        System.out.println(t.process());
    }
}
