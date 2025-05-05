package ru.vladislav117.javawriter.utils;

public final class IndentUtils {
    public static String indentByLevel(int indentLevel) {
        return " ".repeat(indentLevel * 4);
    }
}
