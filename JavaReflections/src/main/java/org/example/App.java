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
        System.out.println( "Testing Java Reflections" );
        Class<Person> refClass = Person.class;
        System.out.println( "Get clazz: "+refClass.getName() );
        Field [] fields = refClass.getFields();
        System.out.println( "Fields: "+fields);
        for (Field f : fields){
            System.out.println( "Get Field: "+ f.getName());
        }

    }
}
