package org.example;

import java.lang.reflect.Field;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Person p = new Person("Leandro", 41);
        System.out.println( "Testing Java Reflections" );
        Class<?> refClass = p.getClass();
        System.out.println( "Get clazz: "+refClass.getName() );
        Field [] fields = refClass.getFields();
        System.out.println( "Fields: "+fields.length);
        for (Field f : fields){
            System.out.println( "Get Field: "+ f.getName());
        }

    }
}
