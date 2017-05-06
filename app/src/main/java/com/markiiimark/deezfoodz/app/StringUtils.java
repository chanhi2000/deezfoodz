package com.markiiimark.deezfoodz.app;

/**
 * Created by MarkiiimarK on 5/7/17.
 */

public class StringUtils {
    private StringUtils() {}

    /**
     * Strips of a potentioal ", " from the front of a string
     *
     * @param inString A string that may have a ", " prefix
     * @return A string with ", " stripped from the front
     */

    public static String stripPrefix(String inString) {
        if (inString.startsWith(", ")) {
            return inString.replaceFirst(", ", "");
        }
        return inString;
    }
}
