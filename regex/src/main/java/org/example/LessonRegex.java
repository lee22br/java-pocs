package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LessonRegex {

    public static void main(String[] args) {
        System.out.println("--- Java Regular Expressions in 3 Steps ---");
        System.out.println("---------------------------------------------------\n");
        step1_basic();
        step2_metacharacters();
        step3_quantifiers();
    }

    /**
     * Step 1: Pattern and Matcher (The Basics)
     */
    public static void step1_basic() {
        System.out.println("Step 1: Pattern and Matcher");
        String text = "In middle of text we can find some patterns Can, can, CAN, CAn";
        String regex = "Can";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("     - Found: " + matcher.group());
        }
        System.out.println("---------------------------------------------------");
    }

    /**
     * Step 2: Metacharacters - Common Patterns
     */
    public static void step2_metacharacters() {
        System.out.println("Step 2: Metacharacters");

        // the . (dot)
        String text1 = "txt, tst, tbt, tlt, tyt, tmt, tnt, txxt, tbbt, t.t";
        String regex1 = "t.t";
        System.out.println("  -> Regex: " + regex1 + " | Text: " + text1);
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(text1);
        while (matcher1.find()) {
            System.out.println("     - Found: " + matcher1.group());
        }
        // The '\d' - Matches a digit (0-9).
        String text2 = "The year is 2025 will be end in 10 days";
        String regex2 = "\\d\\d\\d\\d";
        System.out.println("  -> Regex: " + regex2 + " | Text: " + text2);
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(text2);
        if (matcher2.find()) {
            System.out.println("     - Found: " + matcher2.group());
        }

        // The '\s' - Matches a whitespace character (space, tab, newline).
        String text3 = "P O BOX";
        String regex3 = "P\\sO\\sBOX";
        System.out.println("  -> Regex: " + regex3 + " | Text: " + text3);
        // Pattern.matches attempts to match the ENTIRE string.
        System.out.println("     - Is it an exact match? " + Pattern.matches(regex3, text3));
        System.out.println("---------------------------------------------------");
    }

    /**
     * Step 3: Quantifiers - Pattern Repetition
     */
    public static void step3_quantifiers() {
        System.out.println("## Step 3: Quantifiers (Repetition)");

        String text = "ca, cab, cax, cabb1b, cabbbbbbb";
        String regex;

        // '+': One or more occurrences of the preceding character/pattern.
        regex = "cab+";
        System.out.println("  -> Regex: " + regex + " (cab, cabb, cabbb...) | Text: " + text);
        Matcher matcherPlus = Pattern.compile(regex).matcher(text);
        while (matcherPlus.find()) {
            System.out.println("     - Found: " + matcherPlus.group());
        }

        // '*': Zero or more occurrences.
        regex = "cax.*"; // Matches "ca" and "cax", "caxx", etc.
        String text2 = "ca, cax, caxx, cax34fggh, cax 4";
        System.out.println("  -> Regex: " + regex + " (ca, cax, caxx...) | Text: " + text2);
        Matcher matcherStar = Pattern.compile(regex).matcher(text2);
        while (matcherStar.find()) {
            System.out.println("     - Found: " + matcherStar.group());
        }

        // '?': Zero or one occurrence (optional).
        regex = "P\\.?\\s?O\\.?";
        String text3 = "P.O, P O, P.O.";
        System.out.println("  -> Regex: " + regex + " (P.O/P O) | Text: " + text3);
        Matcher matcherQuestion = Pattern.compile(regex).matcher(text3);
        while (matcherQuestion.find()) {
            System.out.println("     - Found: " + matcherQuestion.group());
        }

        // '{n}': Exactly 'n' occurrences.
        // '{n,}': At least 'n' occurrences.
        // '{n,m}': Between 'n' and 'm' occurrences.
        regex = "\\d{2,4}"; // Min 2, Max 4 digits
        String text4 = "1, 10, 100, 1000, 10000";
        System.out.println("  -> Regex: " + regex + " (2 to 4 digits) | Text: " + text4);
        Matcher matcherRange = Pattern.compile(regex).matcher(text4);
        while (matcherRange.find()) {
            System.out.println("     - Found: " + matcherRange.group());
        }
        System.out.println("---------------------------------------------------");
    }
}
