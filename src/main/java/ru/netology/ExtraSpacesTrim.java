package ru.netology;

public class ExtraSpacesTrim {
    public static String extraTrim(String input) {
        input = input.trim();
        input = input.replaceAll("\\s+", " ");
        return input.replaceAll("\\s+(?=\\p{Punct})","");
    }
}
