package ru.vladislav117.javawriter.klass;

public enum ClassType {
    CLASS("class "),
    ABSTRACT_CLASS("abstract class "),
    INTERFACE("interface "),
    ENUM("enum "),
    RECORD("record ");

    private final String prefix;

    ClassType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
