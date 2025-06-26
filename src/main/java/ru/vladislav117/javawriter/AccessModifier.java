package ru.vladislav117.javawriter;

public enum AccessModifier {
    NOTHING(""),
    PRIVATE("private "),
    PROTECTED("protected "),
    PUBLIC("public ");

    private final String prefix;

    AccessModifier(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
