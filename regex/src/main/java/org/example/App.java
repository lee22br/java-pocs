package org.example;


import java.util.regex.Pattern;

/**
 * Queue world!
 *
 */
public class App 
{
    private static final Pattern PO_BOX_PATTERN = Pattern.compile(
            "\\s+PO\\s+BOX$" +
                    "P\\.O|" +
                    "RR\\s+BOX|"+
                    "PO\\s+BOX",
            Pattern.CASE_INSENSITIVE
    );

    public static boolean checkPoBoxV2(String fullStreet) {
        if (null == fullStreet || fullStreet.isEmpty()) {
            return  false;
        }

        if (PO_BOX_PATTERN.matcher(fullStreet).find()){
            return true;
        }

        //Special Case
        String upperStreet = fullStreet.toUpperCase();
        return upperStreet.contains("HC") && upperStreet.contains("BOX");
    }

    public static void main( String[] args )
    {
        System.out.println("Regex tests");

        //Tests
        String [] addresses = {
                "123 Main Street PO BOX",
                "456 Elm P.O. Box 123",
                "789 Oak RR BOX 45",
                "321 Pine PO BOX 789",
                "HC 1 BOX 324",
                "555 Normal Stree"
        };

        for (String address : addresses) {
            System.out.println(address + " -> " + checkPoBoxV2(address));
        }

    }
}


